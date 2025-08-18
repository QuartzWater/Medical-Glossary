/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;
/**
 *
 * @author BRIN
 */
public class SVGIconRenderer {
    // Helper method to create an ImageIcon from an SVG resource path
    public static ImageIcon createSvgIcon(String resourcePath, int width, int height) {
        try {
            // Get the URL of the SVG resource
            URL svgUrl = SVGIconRenderer.class.getResource(resourcePath);
            if (svgUrl == null) {
                System.err.println("SVG Icon resource not found: " + resourcePath);
                return null;
            }

            // Parse the SVG document
            String parser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
            SVGDocument svgDocument = factory.createSVGDocument(svgUrl.toString());

            // Render the SVG to a BufferedImage
            BufferedImage bufferedImage = renderSvg(svgDocument, width, height);

            return new ImageIcon(bufferedImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static BufferedImage renderSvg(SVGDocument doc, int width, int height) {
        // 1. Build the GVT (Graphics Vector Tree) from the SVG document
        UserAgentAdapter userAgent = new UserAgentAdapter();
        GVTBuilder builder = new GVTBuilder();
        GraphicsNode gvtRoot = builder.build(new org.apache.batik.bridge.BridgeContext(userAgent), doc);

        // 2. Create a BufferedImage to draw the SVG onto
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        // 3. Set high-quality rendering hints for a crisp image
        // This is the most important step for improving quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        // 4. Calculate the scaling ratio to fit the image without distortion
        double docWidth = doc.getRootElement().getWidth().getBaseVal().getValue();
        double docHeight = doc.getRootElement().getHeight().getBaseVal().getValue();
        double scaleX = (double) width / docWidth;
        double scaleY = (double) height / docHeight;
        double scale = Math.min(scaleX, scaleY);

        // 5. Apply the scaling and draw the SVG
        g2d.scale(scale, scale);
        gvtRoot.paint(g2d);
        g2d.dispose();

    return bufferedImage;
}
    
}
