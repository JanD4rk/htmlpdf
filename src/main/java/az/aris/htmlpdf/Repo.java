package az.aris.htmlpdf;

import com.itextpdf.html2pdf.HtmlConverter;
import org.jsoup.Jsoup;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

@RestController
public class Repo {


    @PostMapping(value = "/convertToPDF", produces = MediaType.APPLICATION_PDF_VALUE)
    public void getImage(HttpServletResponse response, @RequestBody DefaultModel model) throws IOException {


        File file = new File("/Users/jand4rk/Desktop/temporary.html");
        FileWriter fileWriter = new FileWriter(file);

        String html = Jsoup.connect(model.url).get().html();
        fileWriter.write(html);
        fileWriter.flush();
        fileWriter.close();


        File pdf = new File("/Users/jand4rk/Desktop/sample.pdf");

        HtmlConverter.convertToPdf(file, pdf);


        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        StreamUtils.copy(new FileInputStream(pdf), response.getOutputStream());

    }

}
