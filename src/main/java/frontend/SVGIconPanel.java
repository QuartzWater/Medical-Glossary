/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import backend.SVGIconRenderer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

/**
 *
 * @author BRIN
 */
public final class SVGIconPanel extends javax.swing.JPanel{
    private SVGDocument svgDocument;
    private GraphicsNode gvtRoot;
    private SAXSVGDocumentFactory factory;
    private URL currentUrl;
    private iconPosition currentPosition;

    public enum iconPosition{
        
        RIGHT,
        CENTER,
        LEFT
    }
    
    public SVGIconPanel(){
        super();
        setOpaque(false);
        
        currentPosition = iconPosition.LEFT;
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        factory = new SAXSVGDocumentFactory(parser);
        
        super.setPreferredSize(new Dimension(50,50));
        
        try {
            setSvgUrl(getClass().getResource("/images/icons/checkmark.svg"));
        } catch (IOException ex) {
            Logger.getLogger(SVGIconPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void setSvgUrl(URL svgUrl) throws IOException {
        Objects.requireNonNull(svgUrl, "URL for SVG icon was found to be null. Please check the correctness of resource path used to initialize this URL.");

        currentUrl = svgUrl;
        // Clear existing resources before loading new ones
        this.svgDocument = null;
        this.gvtRoot = null;

        // Parse the SVG document
        SVGDocument tempSVGDoc = factory.createSVGDocument(svgUrl.toString());
        

        Objects.requireNonNull(tempSVGDoc, "Either the URL points towards a malformed SVG file, or a non-SVG File, or the location is inaccessible at the time of retrival of SVG file.");
        // Build the GVT (Graphics Vector Tree)
        
        GraphicsNode tempGVT = buildGVT(tempSVGDoc);
        
        Objects.requireNonNull(tempGVT, "Scalable Vector Graphics Node could not be formed properly...");
        
        
        this.svgDocument = tempSVGDoc;
        this.gvtRoot = tempGVT;
        
        // Revalidate and repaint the panel
        callRevalidateAndRepaint();
    }
    
    public void setIconPostion(iconPosition type){
        
        this.currentPosition = type;
        repaint();
    }
    
    public iconPosition getIconPosition(){
        
        return currentPosition;
    }
    public void callRevalidateAndRepaint(){
        
        super.revalidate();
        super.repaint();
    }

    public URL getSVGUrl(){
        
        return this.currentUrl;
    }
   
    private GraphicsNode buildGVT(SVGDocument doc) {
        
        if(doc == null){
            return null;
        }
        // 1. Create a UserAgent. This object provides information about the
        //    environment for rendering, such as font families and language.
        UserAgentAdapter userAgent = new UserAgentAdapter();

        // 2. Create a BridgeContext. This is a crucial part of the Batik rendering
        //    pipeline. It manages how SVG elements are converted to GVT nodes.
        BridgeContext bridgeContext = new BridgeContext(userAgent);

        // 3. Create a GVTBuilder. This is the main class that constructs the GVT from
        //    the SVGDocument.
        GVTBuilder builder = new GVTBuilder();

        // 4. Build the GVT. This is the method call that performs the conversion.
        //    The returned GraphicsNode is the root of the GVT.
        return builder.build(bridgeContext, doc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if(gvtRoot == null || svgDocument == null){
            System.out.println("Internal Error: Could not paint graphics due to null values of Graphics Node and SVGDocument.");
            return;
        }
        // Apply high-quality rendering hints
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        
        
        
        // Get the SVG's intrinsic dimensions from the viewBox
        double svgWidth = svgDocument.getRootElement().getWidth().getBaseVal().getValue();
        double svgHeight = svgDocument.getRootElement().getHeight().getBaseVal().getValue();

        System.out.println(svgWidth);
        System.out.println(svgHeight);
        
        // Get the current panel dimensions
        Dimension dim = super.getSize();
        System.out.println(dim.toString());
        double panelWidth = dim.getWidth();
        double panelHeight = dim.getHeight();
        
        // Calculate the scaling transformation
        double scaleX = panelWidth / svgWidth;
        double scaleY = panelHeight / svgHeight;
        double scale = Math.min(scaleX, scaleY);
        
        
        
        if(currentPosition == iconPosition.CENTER){
            // Calculate the new, scaled dimensions of the SVG
            double scaledWidth = svgWidth * scale;
            double scaledHeight = svgHeight * scale;

            // Calculate the translation offsets to center the scaled image
            double translateX = (panelWidth - scaledWidth) / 2.0;
            double translateY = (panelHeight - scaledHeight) / 2.0;

            // Apply the transformations: first translation, then scaling
            // The order is important. We translate the drawing area and then scale it.
            g2d.translate(translateX, translateY);
        }
        else if (currentPosition == iconPosition.RIGHT) {
            double scaledWidth = svgWidth * scale;
            double scaledHeight = svgHeight * scale;

            // Calculate the translation offsets
            // To right-align, the horizontal translation is the panel's width minus the scaled image's width.
            double translateX = panelWidth - scaledWidth;
            // To vertically center, the translation is the remaining height divided by 2.
            double translateY = (panelHeight - scaledHeight);

            // Apply the transformations: first translation, then scaling
            g2d.translate(translateX, translateY);
        }
        
        g2d.scale(scale, scale);
        // Draw the SVG content
        
        gvtRoot.paint(g2d);
        g2d.dispose();
        
    }
    
    // NOT SURE IF I SHOULD OVERRIDE THIS METHOD >> SUGGESTED BY GEMINI, BUT I FEEL THIS CAN BREAK SOMETHING
    /*
    @Override
    public Dimension getPreferredSize() {
        if (svgDocument != null) {
            double svgWidth = svgDocument.getRootElement().getWidth().getBaseVal().getValue();
            double svgHeight = svgDocument.getRootElement().getHeight().getBaseVal().getValue();
            return new Dimension((int) Math.ceil(svgWidth), (int) Math.ceil(svgHeight));
        }
        return new Dimension(50,50);
    }
    */
}
