package com.strzelecki.ratingapp.Services;

import com.strzelecki.ratingapp.Model.Category;
import com.strzelecki.ratingapp.Model.Periodic;
import com.strzelecki.ratingapp.Model.PeriodicWithData;
import com.strzelecki.ratingapp.Model.ResurchifyTable;
import com.strzelecki.ratingapp.Repos.PeriodicRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PeriodicServiceImpl implements PeriodicService{

    @Autowired
    PeriodicRepo periodicRepo;


    @Override
    public List<Periodic> getByCategory(Set<Category> categories) {

        List<Periodic> periodicList = periodicRepo.findAllByCategoriesIn(categories).get();



        return periodicList;
    }

    @Override
    public List<PeriodicWithData> retrieved(List<Periodic> periodics) {

        List<PeriodicWithData> wrapped = periodics.stream().limit(20).map(x->{
            PeriodicWithData periodicWithData = new PeriodicWithData();
            periodicWithData.setTitle(x.getTitle());
            periodicWithData.setHIndexSciMagor(getFromScimagor(x.getIssn()));
            periodicWithData.setResurchifyData(getFromResurchify(x.getIssn()));
            System.out.println(periodicWithData.getTitle() + periodicWithData.getHIndexSciMagor() );
            System.out.println("Resurczifaj: " + periodicWithData.getResurchifyData());
            return periodicWithData;
        }).collect(Collectors.toList());





        return wrapped;
    }

    public List<ResurchifyTable> getFromResurchify(String issn){


        String svProtocol1 = "https://www.resurchify.com/";
        String htmlRes1 = htmlgetter( "https://www.resurchify.com/impact-factor-search.php?query=" + issn.replaceAll("[\\s\\-()]", "") );
        Document document1 = Jsoup.parse(htmlRes1);
        Elements sectionsRes = document1.select("a");
        String searchVarRes = sectionsRes.get(4).attr("href");
        svProtocol1 = new StringBuilder(svProtocol1).append(searchVarRes).toString();
        String htmlRes2 = htmlgetter(svProtocol1);
        Document documentRes2 = Jsoup.parse(htmlRes2);
        Element table = documentRes2.select("table").get(0);
        Elements rows = table.select("tr");
        List<ResurchifyTable> resurchifyTable = new ArrayList<>();

        for (int i = 1; i<rows.size(); i++) {
            Element row = rows.get(i);
            Elements cols = rows.get(i).select("td");
            if(cols.size()==3) {
                String str1 = cols.get(1).text();
                if (isNumeric(str1)) {
                    ResurchifyTable rt = new ResurchifyTable();
                    rt.setYear(Integer.parseInt(cols.get(0).text()));
                    rt.setCitations(Integer.parseInt(cols.get(2).text()));
                    rt.setImpactFactor(Double.parseDouble(cols.get(2).text()));
                    resurchifyTable.add(rt);


                }
            }else{
                System.out.println("Cuś się zjebało");
                System.out.println(svProtocol1);

                System.out.println(row.getAllElements().text());
            }
        }



return resurchifyTable;

    }



    public String getFromScimagor(String issn){

        String svProtocol = "https://www.scimagojr.com/";
        String html = htmlgetter("https://www.scimagojr.com/journalsearch.php?q=" +issn.replaceAll("[\\s\\-()]", ""));
        Document doc = Jsoup.parse(html);
        Elements sections = doc.select("a");
        String searchVar2 = sections.get(8).attr("href");
        svProtocol = new StringBuilder(svProtocol).append(searchVar2).toString();
        String html2 = htmlgetter(svProtocol);
        Document document = Jsoup.parse(html2);
        Elements element1 = document.select("p.hindexnumber");
        System.out.println(svProtocol);
        return element1.text();
    }



    private static String htmlgetter(String a){
        URL url;
        StringBuilder sb =  new StringBuilder();
        try {

            url = new URL(a);
            URLConnection conn = url.openConnection();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            br.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String html = sb.toString();
        return html;

    }

    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}
