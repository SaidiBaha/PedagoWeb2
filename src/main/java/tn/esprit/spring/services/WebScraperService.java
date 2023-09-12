package tn.esprit.spring.services;


import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.ParagrapheScraped;
import tn.esprit.spring.entity.SousCompetence;
import tn.esprit.spring.repositories.ParagrapheScrapedRepository;

import java.io.IOException;

@AllArgsConstructor
@Service
/*

public class WebScraperService {

    private ParagrapheScrapedRepository paragrapheScrapedRepository;
    public String scrapeWebsite(String url) throws IOException {

        Document doc = Jsoup.connect(url).get();
        Elements body = doc.select("div.entry-content.clearfix p");

        StringBuilder scrapedContent = new StringBuilder(); // Accumulateur du contenu
        //System.out.println(doc.body());
        for (Element paragraph : body) {
            if (!paragraph.select("p.single-entry-meta.pe-subheader").isEmpty()) {
                continue; // Ignorer ce paragraphe et passer au suivant
            }
            if (paragraph.select("a.auth-href").hasAttr("href")) {
                continue; // Ignorer ce paragraphe et passer au suivant
            }

            String text = paragraph.text();
            scrapedContent.append(text).append("\n"); // Ajoute le texte au contenu accumulé


        }

        //System.out.println(paragraph.text());
        //System.out.println("---------------------------------------");
        return scrapedContent.toString(); // Renvoie le contenu accumulé
    }
}

 */
public class WebScraperService {
    private  ParagrapheScrapedRepository paragrapheScrapedRepository;



    public String scrapeAndSaveToDatabase(String url, SousCompetence sousCompetence ) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements body = doc.select("div.entry-content.clearfix p");

        StringBuilder scrapedContent = new StringBuilder(); // Accumulateur du contenu
        ParagrapheScraped paragraphe = new ParagrapheScraped();
        for (Element paragraph : body) {
            if (!paragraph.select("p.single-entry-meta.pe-subheader").isEmpty() ||
                    paragraph.select("a.auth-href").hasAttr("href")) {
                continue; // Ignorer les paragraphes non pertinents
            }

            String text = paragraph.text();
            scrapedContent.append(text).append("\n"); // Ajoute le texte au contenu accumulé


            paragraphe.setContenu(scrapedContent.toString());
            paragraphe.setSousCompetence(sousCompetence);
        }
        paragrapheScrapedRepository.save(paragraphe); // Enregistrer le paragraphe dans la base de données
        return scrapedContent.toString();
    }
}


/*

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://www.imdb.com/chart/top/").get();
        Elements body = doc.select("ul.ipc-metadata-list");
        //System.out.println(body.select("li").size());


        for (Element e : body.select("li"))
        {
          String img =  e.select("div.ipc-media img").attr("src");
           System.out.println(img);
            String title =  e.select("h3.ipc-title__text").text();
            System.out.println(title);
            String spans =  e.select("div.sc-14dd939d-5.cPiUKY.cli-title-metadata span.sc-14dd939d-6.kHVqMR.cli-title-metadata-item ").text().trim(); //.replaceAll("[^\\d]",     "");
            System.out.println(spans);
        }
  }

 */


