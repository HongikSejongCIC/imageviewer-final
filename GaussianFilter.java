
import java.awt.Color;

public class GaussianFilter extends Filter {

    /**
     * Constructor for objects of class GrayScaleFilter.
     *
     * @param name The name of the filter.
     */
    public GaussianFilter(String name) {
        super(name);
    }

    /**
     * Apply this filter to an image.
     *
     * @param image The image to be changed by this filter.
     */
    public void apply(OFImage image) {
        
        OFImage original = new OFImage(image);
        
        int[][] mask = {{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};
        
        int maskMax = 16;
        
        int height = image.getHeight();
        int width = image.getWidth();

       
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                int r = 0;
                int g = 0;
                int b = 0;
                
                for (int mr = 0; mr < 3; mr++) {
                    for (int mc = 0; mc < 3; mc++) {
                        
                        Color pix = null;
                        try {
                            pix = original.getPixel(x + mc - 1, y + mr - 1); //마스크 계산을 위해 이미지에서 픽셀을 가져옵니다.
                        } catch (Exception e) {
                            break;
                        }
                        
                        r += mask[mr][mc] * pix.getRed();
                        g += mask[mr][mc] * pix.getGreen();
                        b += mask[mr][mc] * pix.getBlue();

                    }
                }
                
                r /= maskMax;
                g /= maskMax;
                b /= maskMax;
                
                r = Math.min(Math.max(r, 0), 255);
                g = Math.min(Math.max(g, 0), 255);
                b = Math.min(Math.max(b, 0), 255);
.
                image.setPixel(x, y, new Color(r, g, b));
            }
        }
    }
}
