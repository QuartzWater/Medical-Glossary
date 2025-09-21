/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.beans.BeanProperty;
import java.beans.JavaBean;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingContainer;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

/**
 *
 * @author BRIN
 */
@JavaBean(description = "An extended version of JPanel, which by default is not a typical Swing Container. And its not meant to be used as a container.")
@SwingContainer(false)
public final class SVGIconPanel extends javax.swing.JPanel{
    private SVGDocument svgDocument;
    private GraphicsNode gvtRoot;
    private BridgeContext bridgeContext;
    private SAXSVGDocumentFactory factory;
    private URL currentURL;
    private String currentURLString;
    
    private iconPosition currentPosition;
    private boolean backgroundPainted;
    private int arcSize;
    
    private Color currentColor;
    
    private double iconPaddingX;
    private double iconPaddingY;
    private double iconSquarePadding;
    
    private double bgPaddingX;
    private double bgPaddingY;
    private double bgSquarepadding;
    
    private static final Color DEFAULT_COLOR = new Color(0,0,0);
    
    public enum iconPosition{
        
        RIGHT,
        CENTER,
        LEFT
    }
    
    public SVGIconPanel(){
        super();
        currentColor = new Color(255,255,255);
        backgroundPainted = false;
        arcSize = 20;
        iconPaddingX = 0;
        iconPaddingY = 0;
        iconSquarePadding = 0;
        bgPaddingX = 0;
        bgPaddingY = 0;
        bgSquarepadding = 0;
        
        setOpaque(false);
        
        currentPosition = iconPosition.LEFT;
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        factory = new SAXSVGDocumentFactory(parser);
        
        super.setPreferredSize(new Dimension(50,50));
        currentURL = getClass().getResource("/images/icons/checkmark.svg");
        try {
            
            set_SVG_URL(currentURL);
        } catch (IOException ex) {
            Logger.getLogger(SVGIconPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // USE set_SVG_URL_String method to set the URL classpath of the resource.
    private void set_SVG_URL(URL svgUrl) throws IOException {
        Objects.requireNonNull(svgUrl, "URL for SVG icon was found to be null. Please check the correctness of resource path used to initialize this URL.");

        currentURL = svgUrl;
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
        super.revalidate();
        super.repaint();
    }
    
    public void set_SVG_URL_String(String toURL) throws IOException{
        currentURLString = toURL;
        
        set_SVG_URL(getClass().getResource(toURL));
        
    }
    
    public void setIconPosition(iconPosition type){
        
        this.currentPosition = type;
        
    }
    
    public void setCurrentColor(Color currentColor){
        this.currentColor = currentColor;
    }
    
    public void setBackgroundPainted(boolean condition){
       
       this.backgroundPainted = condition;
    }
    
    public void setArcSize(int size){
        
        this.arcSize = size;
    }
    
    public void setIconSquarePadding(double sqrPad){
        
        this.iconSquarePadding = sqrPad;
        this.iconPaddingX = sqrPad;
        this.iconPaddingY = sqrPad;
    }
    
    public void setIconRectangularPadding(double[] pad){
        
        
        if(pad.length > 2 || pad.length < 2){

            throw new IllegalArgumentException("Integer padding array can not be greater than 2 or less than 2.");
        }
        
        this.iconPaddingX = pad[0];
        this.iconPaddingY = pad[1];
    }
    
    public void setIconRectangularPadding(double padX, double padY){
        
        this.iconPaddingX = padX;
        this.iconPaddingY = padY;
    }
    
    public void setbgSquarePadding(double sqrPad){
        
        this.bgSquarepadding = sqrPad;
        this.bgPaddingX = sqrPad;
        this.bgPaddingY = sqrPad;
    }
    
    public void setbgRectangularPadding(double[] pad){
        
        if(pad.length > 2 || pad.length < 2){

            throw new IllegalArgumentException("Integer padding array can not be greater than 2 or less than 2.");
        }
        
        this.bgPaddingX = pad[0];
        this.bgPaddingY = pad[1];
    }
    
    public void setbgRectangularPadding(int padX, int padY){
        
        this.bgPaddingX = padX;
        this.bgPaddingY = padY;
        
    }
    
    /** Overrides the current fill color of a path contained in the SVG icon.
     * <p>
     * If provided {@code pathID} was not found. The method returns immediately.
     * </p>
     * 
     * <p>
     * Valid formats for {@code value} are:
     * <br>- Hexadecimal formats (e.g. "#ff0000" for red, "#008000" for green etc.)</br>
     * <br>- RGB function (e.g. "rgb(255,255,255)" for white of "rgb(0%, 0%, 100%)" for blue)</br>
     * <br>- Predefined CSS keywords (e.g. "red", "blue", "transparent" etc.)</br>
     * </p>
     * 
     * 
     * @param pathID The path ID whose fill color is to be overridden
     * @param value The new value of color and should follow one of the standard format mentioned above.
     */
    public void overrideFillColor(String pathID, String value){
        
        
        Element element = svgDocument.getElementById(pathID);
        
        if(element == null){
            return;
        }
        element.setAttribute("fill", value);
        this.repaint();
    }
    
    /** Overrides the current stroke color of a path contained in the SVG icon.
     * <p>
     * If provided {@code pathID} was not found. The method returns immediately.
     * </p>
     * 
     * <p>
     * Valid formats for {@code value} are:
     * <br>- Hexadecimal formats (e.g. "#ff0000" for red, "#008000" for green etc.)</br>
     * <br>- RGB function (e.g. "rgb(255,255,255)" for white of "rgb(0%, 0%, 100%)" for blue)</br>
     * <br>- Predefined CSS keywords (e.g. "red", "blue", "transparent" etc.)</br>
     * </p>
     * 
     * 
     * @param pathID The path ID whose stroke color is to be overridden
     * @param value The new value of color and should follow one of the standard format mentioned above.
     */
    public void overrideStrokeColor(String pathID, String value){
        
        Element element = svgDocument.getElementById(pathID);
        
        if(element == null){
            return;
        }
        element.setAttribute("stroke", value);
        this.repaint();
    }
    
    
    /** Overrides the current fill opacity of a path contained in the SVG icon.
     * <p>
     * If provided {@code pathID} was not found. The method returns immediately.
     * </p>
     * 
     * 
     * @param pathID The path ID whose fill opacity is to be overridden
     * @param value The new value of opacity between 0.0 (transparent) to 1.0 (opaque)
     */
    public void overrideFillOpacity(String pathID, double value){
        
        Element element = svgDocument.getElementById(pathID);
        
        if(element == null){
            return;
        }
        element.setAttribute("fill-opacity", Double.toString(value));
        this.repaint();
    }
    
    /** Overrides the current stroke opacity of a path contained in the SVG icon.
     * <p>
     * If provided {@code pathID} was not found. The method returns immediately.
     * </p>
     * 
     * @param pathID The path ID whose stroke opacity is to be overridden
     * @param value The new value of opacity between 0.0 (transparent) to 1.0 (opaque)
     */
    public void overrideStrokeOpacity(String pathID, double value){
        
        Element element = svgDocument.getElementById(pathID);
        
        if(element == null){
            return;
        }
        element.setAttribute("stroke-opacity", Double.toString(value));
        this.repaint();
    }
    
    /** Overrides the current fill attributes (color and opacity) of a path contained in the SVG icon.
     * <p>
     * If provided {@code pathID} was not found. The method returns immediately.
     * </p>
     * 
     * @param pathID The path ID whose fill color is to be overridden
     * @param color The new value of color (r,g,b,a)
     */
    
    public void overrideFill(String pathID, Color color){
        
        Element element = svgDocument.getElementById(pathID);
        
        if(element == null){
            System.out.println("nullllllll");
            return;
        }
        String hex = String.format("#%06X", (0xFFFFFF & color.getRGB()));
        element.setAttributeNS(null, "fill", hex);
        
        double opacity = color.getAlpha()/255.0;
        element.setAttributeNS(null, "fill-opacity", Double.toString(opacity));
        
        this.repaint();
    }
    
    /** Overrides the current stroke attributes (color and opacity) of a path contained in the SVG icon.
     * <p>
     * If provided {@code pathID} was not found. The method returns immediately.
     * </p>
     * 
     * 
     * @param pathID The path ID whose stroke color is to be overridden
     * @param color The new value of color (r,g,b,a)
     */
    public void overrideStroke(String pathID, Color color){
        
        Element element = svgDocument.getElementById(pathID);
        
        if(element == null){
            return;
        }
        String hex = String.format("#%06X", (0xFFFFFF & color.getRGB()));
        element.setAttribute("stroke", hex);
        
        double opacity = color.getAlpha()/255.0;
        element.setAttribute("stroke-opacity", Double.toString(opacity));
        this.repaint();
    }
    
    // ******************** GETTERS **************************** //
    
    @BeanProperty(preferred = true, visualUpdate = true, description
            = "SVG File as actual URL. Use _SVG_URL_String property to set the classpath of the resource.")
    public URL get_SVG_URL(){
        
        return this.currentURL;
    }
    
    @BeanProperty(preferred = true, visualUpdate = true, description
            = "SVG File as URL this String points towards.")
    public String get_SVG_URL_String(){
        
        return this.currentURLString;
    }
    
    @BeanProperty(preferred = true, visualUpdate = true, description
            = "The position of icon, actual position may differ based on Icon Size.")
    public iconPosition getIconPosition(){
        
        return currentPosition;
    }
    
    @BeanProperty(preferred = true, visualUpdate = true, description
            = "Background color (If painted) of the component.")
    public Color getCurrentColor(){
        
        return this.currentColor;
    }
   
    @BeanProperty(preferred = true, visualUpdate = true, description
            = "Should background color be painted.")
    public boolean isBackgroundPainted(){
       
        return this.backgroundPainted;
    }
   
    @BeanProperty(preferred = true, visualUpdate = true, description
            = "Defines the degree of roundness for background (if painted).")
    public int getArcSize(){
        
        return this.arcSize;
    }
    
    @BeanProperty(preferred = true, visualUpdate = true, description
            = "Defines same amount of icon padding for both x and y directions.")
    public double getIconSquarePadding(){
        
        return this.iconSquarePadding;
    }
    
    @BeanProperty(preferred = true, visualUpdate = true, description
            = "Defines different icon padding for x and y directions.")
    public double[] getIconRectangularPadding(){
        double array[] = {
        
            iconPaddingX,
            iconPaddingY
        };
        return array;
    }
    
    @BeanProperty(preferred = true, visualUpdate = true, description
            = "Defines same amount of background padding (if painted) for both x and y directions.")
    public double getbgSquarePadding(){
        
        return this.bgSquarepadding;
    }
    
    @BeanProperty(preferred = true, visualUpdate = true, description
            = "Defines different background padding (if painted) for x and y directions.")
    public double[] getbgRectangularPadding(){
        double array[] = {
            
            bgPaddingX,
            bgPaddingY
        };
        return array;
    }
    
    
    public Color getCurrentFill(String pathID){
        Element elementById = svgDocument.getElementById(pathID);
        if(elementById == null){
            return DEFAULT_COLOR;
        }
        
        String hex = elementById.getAttribute("fill");
        Color c;
        try {
            c = Color.decode(hex);
        }catch (NumberFormatException e){
            
            return DEFAULT_COLOR;
        }
        
        return c;
    }
    
    public Color getCurrentStroke(String pathID){
        Element elementById = svgDocument.getElementById(pathID);
        if(elementById == null){
            return DEFAULT_COLOR;
        }
        
        String hex = elementById.getAttribute("stroke");
        Color c;
        try {
            c = Color.decode(hex);
        }catch (NumberFormatException e){
            return DEFAULT_COLOR;
        }
        
        return c;
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
        bridgeContext = new BridgeContext(userAgent);
        bridgeContext.setDynamicState(BridgeContext.DYNAMIC); // HOLY SHIT THIS ADDITION TOOK ME THREE HOURS OF MALDING WITH REDDIT, GEMINI AND CHATGPT. (CHATGPT TOLD ME THIS AFTER THREE RETRIES)

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
        
        if(backgroundPainted){
            double width = getWidth() - (2 * bgPaddingX);
            double height = getHeight() - (2 * bgPaddingY) ;

            // Draw the main button shape on top of the shadow
            g2d.setColor(currentColor);
            g2d.fill(new RoundRectangle2D.Double(bgPaddingX, bgPaddingY, width, height, arcSize, arcSize));
            
        }
        
        // Get the SVG's intrinsic dimensions from the viewBox
        double svgWidth = svgDocument.getRootElement().getWidth().getBaseVal().getValue();
        double svgHeight = svgDocument.getRootElement().getHeight().getBaseVal().getValue();
        
        // Get the current panel dimensions
        Dimension dim = super.getSize();
        double panelWidth = dim.getWidth();
        double panelHeight = dim.getHeight();
        
        // Calculate the scaling transformation
        double scaleX = panelWidth / (svgWidth + iconPaddingX);
        double scaleY = panelHeight / (svgHeight + iconPaddingY);
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
    
    
    // *********************** RESETTING ANNOTATIONS ************************* //
    
    
    @BeanProperty(hidden = true, description
            = "Use setCurrentColor to change the background. There is no use of changing Foreground for this component.")
    @Override
    public void setForeground(Color setColor){
        
        super.setForeground(setColor);
    }
    
    @BeanProperty(hidden =  true,  description
            = "The foreground of this component.")
    @Override
    public Color getForeground(){
        
        return super.getForeground();
    };
    
    @BeanProperty(hidden = true, description
            = "Not useful method for this class. Use setCurrentColor to set Background of this component.")
    @Override
    public void setBackground(Color toSet){
        super.setBackground(toSet);
    }
    
    @BeanProperty(hidden = true, description
            = "The background of this component.")
    
    @Override
    public Color getBackground(){
        
        return super.getBackground();
    }
    
    @BeanProperty(hidden = true, description
            = "Calls setBorder method of superclass. Not particularly useful for this component.")
    @Override
    public void setBorder(javax.swing.border.Border border){
        super.setBorder(border);
    }
    
    @BeanProperty(hidden = true, description
            = "Calls getBorder method of superclass. Not particularly useful for this component.")
    @Override
    public javax.swing.border.Border getBorder(){
        return super.getBorder();
    }
    
    
    @BeanProperty(hidden = true, description
            = "Calls setToolTipText method of superclass. Not particularly useful for this component.")
    @Override
    public void setToolTipText(String toSet){
        super.setToolTipText(toSet);
    }
    
    @BeanProperty(hidden = true, description
            = "Calls getToolTipText method of superclass. Not particularly useful for this component.")
    @Override
    public String getToolTipText(){
        return super.getToolTipText();
    }
}
