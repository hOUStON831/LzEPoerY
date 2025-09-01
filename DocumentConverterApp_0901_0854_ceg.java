// 代码生成时间: 2025-09-01 08:54:30
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Main application class for the Document Converter REST service.
 */
@ApplicationPath("/api")
public class DocumentConverterApp extends Application {

    /**
     * Converts an XML document to JSON format.
     *
     * @param xmlPath Path to the XML file.
     * @return JSON representation of the XML document.
     */
    @Path("/convert")
    public static class DocumentConverterResource {

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response convertXMLToJson(@QueryParam("xmlPath") String xmlPath) {
            try {
                // Read XML file content
                String xmlContent = new String(Files.readAllBytes(Paths.get(xmlPath)));

                // Parse XML content to Document object
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(new InputSource(new StringReader(xmlContent)));

                // Transform XML to JSON
                return Response.ok(convertXMLToJson(doc)).build();
            } catch (IOException | SAXException | Exception e) {
                // Handle exceptions and return error response
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
            }
        }

        /**
         * Converts an XML Document to JSON string.
         *
         * @param doc XML Document to convert.
         * @return JSON string representation of the XML Document.
         * @throws Exception If conversion fails.
         */
        private static String convertXMLToJson(Document doc) throws Exception {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            // Create a JSON string from XML Document
            StringWriter writer = new StringWriter();
            transformer.transform(source, new StreamResult(writer));
            return writer.toString();
        }
    }
}
