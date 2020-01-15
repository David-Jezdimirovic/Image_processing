/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs103.pz;

import java.lang.Object;


import com.sun.javafx.image.PixelUtils;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;



/**
 *
 * @author Lenovo
 */
public class Functions{

    public BufferedImage changeBrightness(BufferedImage image, int val) {

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color color = new Color(image.getRGB(x, y));

                int r = color.getRed(), g = color.getGreen(), b = color.getBlue();

                r = checkColorRange(color.getRed() + val);
                g = checkColorRange(color.getGreen() + val);
                b = checkColorRange(color.getBlue() + val);

                color = new Color(r, g, b);
                image.setRGB(x, y, color.getRGB());
            }
        }

        return image;
    }

    public BufferedImage changeContrast(BufferedImage image, double val) {
        
        int width = image.getWidth();
        int height = image.getHeight();
        double factor = (259 * (val + 255)) / (255 * (259 - val));
        
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                Color color = new Color(image.getRGB(x, y));

                int a = color.getAlpha(), r = color.getRed(), g = color.getGreen(), b = color.getBlue();

                r = checkColorRange((int) (factor * (color.getRed() - 128) + 128));
                g = checkColorRange((int) (factor * (color.getGreen() - 128) + 128));
                b = checkColorRange((int) (factor * (color.getBlue() - 128) + 128));

                color = new Color(r, g, b);

                image.setRGB(x, y, color.getRGB());

            }

        }
//         double factor = (259 * (val + 255)) / (255 * (259 - val));
//         
//         for (int x = 0, x2 = width-1; x < image.getWidth()/2; x++, x2--) {
//            for (int y = 0, y2 = height-1; y < image.getHeight()/2; y++, y2--) {
//
//               
//                
//                Color colorxy = new Color(image.getRGB(x, y));
//             //   int a = colorxy.getAlpha(), r = colorxy.getRed(), g = colorxy.getGreen(), b = colorxy.getBlue();
//             int   r = checkColorRange((int) (factor * (colorxy.getRed() - 128) + 128));
//             int   g = checkColorRange((int) (factor * (colorxy.getGreen() - 128) + 128));
//              int  b = checkColorRange((int) (factor * (colorxy.getBlue() - 128) + 128));
//                colorxy = new Color(r, g, b);
//                
//                image.setRGB(x, y, colorxy.getRGB());
//                
//                Color colorx2y = new Color(image.getRGB(x2, y));
//                //int a2 = colorx2y.getAlpha(), r2 = colorx2y.getRed(), g2 = colorx2y.getGreen(), b2 = colorx2y.getBlue();
//               int r2 = checkColorRange((int) (factor * (colorx2y.getRed() - 128) + 128));
//               int g2 = checkColorRange((int) (factor * (colorx2y.getGreen() - 128) + 128));
//               int b2 = checkColorRange((int) (factor * (colorx2y.getBlue() - 128) + 128));
//                colorx2y = new Color(r2, g2, b2);
// 
//                image.setRGB(x2, y, colorx2y.getRGB());
//                
//                
//                Color colorxy2 = new Color(image.getRGB(x, y2));
//              //  int a3 = colorxy2.getAlpha(), r3 = colorxy2.getRed(), g3 = colorxy2.getGreen(), b3 = colorxy2.getBlue();
//              int  r3 = checkColorRange((int) (factor * (colorxy2.getRed() - 128) + 128));
//              int  g3 = checkColorRange((int) (factor * (colorxy2.getGreen() - 128) + 128));
//              int  b3 = checkColorRange((int) (factor * (colorxy2.getBlue() - 128) + 128));
//                colorxy2 = new Color(r3, g3, b3);
// 
//                image.setRGB(x, y2, colorxy2.getRGB());
//                
//                 Color colorx2y2 = new Color(image.getRGB(x2, y2));
//              //  int a4 = colorx2y2.getAlpha(), r4 = colorx2y2.getRed(), g4 = colorx2y2.getGreen(), b4 = colorx2y2.getBlue();
//              int  r4 = checkColorRange((int) (factor * (colorx2y2.getRed() - 128) + 128));
//              int  g4 = checkColorRange((int) (factor * (colorx2y2.getGreen() - 128) + 128));
//              int  b4 = checkColorRange((int) (factor * (colorx2y2.getBlue() - 128) + 128));
//                colorx2y2 = new Color(r4, g4, b4);
// 
//                image.setRGB(x2, y2, colorx2y2.getRGB());
//            }
//
//        }
        return image;

    }
    
    
    
     public BufferedImage changeBlur(BufferedImage image, double val) {
                    
        int radius = (int)val;
//        int h = (int)(radius*1.5);
        int size = (int)(radius * 1.5) ;
        float weight = 1.0f / (size * size);
        float[] data = new float[size * size];

        for (int i = 0; i < data.length; i++) {
            data[i] = weight;
        }

        Kernel kernel = new Kernel(size, size, data);
//                    Kernel kernel = new Kernel(5, 5, new float[]{1f/(5*5),1f/(5*5),1f/(5*5),
//                        1f/(5*5),1f/(5*5),1f/(5*5),1f/(5*5),1f/(5*5),1f/(5*5),1f/(5*5),1f/(5*5),1f/(5*5)
//                    ,1f/(5*5),1f/(5*5),1f/(5*5),1f/(5*5),1f/(5*5),1f/(5*5),1f/(5*5),1f/(5*5),1f/(5*5)
//                    ,1f/(5*5),1f/(5*5),1f/(5*5),1f/(5*5)});

       ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
//         ConvolveOp op = new ConvolveOp(kernel);

         BufferedImage blurred = op.filter(image, null);
//       image = op.filter(image, null);

//int radius = 3;
//        int kernelSize = radius*radius;
////        float[] data = new float[kernelSize * kernelSize];
////         for (int i = 0; i < data.length; i++) {
////             data[i]= 1.0f / (kernelSize * kernelSize);
////         }
////         Kernel kernel = new Kernel(kernelSize, kernelSize, data);
//         float[][] kernel = new float[radius][radius];
//         for (int i = 0; i < radius; i++) {
//             for (int j = 0; j < radius; j++) {
//                 kernel[i][j]= 1.0f / (kernelSize * kernelSize);
//             }
//             
//         }
//          BufferedImage blurred = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
//          blurred.setData(image.getData());
//         
//          for (int x = 1; x < image.getWidth()-1; x++) { 
//              for (int y = 1; y < image.getHeight()-1; y++) {
//                  int sum = 0;
//                  for (int xk = 0,x2=0; xk < kernel.length; xk++,x2++) {
//                      for (int yk = 0,y2=0; yk < kernel.length; yk++,y2++) {
//                          int a = image.getRGB(xk-1, yk-1);
//                          float b = a * kernel[xk-1][yk-1];
//                          sum += b;
////                          System.out.println(sum);
//                      }    
//                  }
//                   blurred.setRGB(x, y, sum/kernelSize);
//              }
//         }

         return blurred;
     }
    
public  BufferedImage blur(BufferedImage image, double val) {  
    //int[] filter = {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 4, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1};
    int radius = (int)val;
    int[] filter = new int[radius * radius];
    int filterWidth = radius;
   
    int j = 1;
    for (int i = 0; i < filter.length; i++) {
        
        if(i == filter.length / 2){
            filter[i] = 4;
        }else if (j % 2 != 0){
            filter[i] = 1;
        }else{
            filter[i] = 2;
        }
        j++;  
    }
    if (filter.length % filterWidth != 0) {
        throw new IllegalArgumentException("filter contains a incomplete row");
    }

    final int width = image.getWidth();
    final int height = image.getHeight();
    final int sum = IntStream.of(filter).sum();

    int[] input = image.getRGB(0, 0, width, height, null, 0, width);

    int[] output = new int[input.length];

    final int pixelIndexOffset = width - filterWidth;
    final int centerOffsetX = filterWidth / 2;
    final int centerOffsetY = filter.length / filterWidth / 2;

    
    // apply filter
    for (int h = height - filter.length / filterWidth + 1, w = width - filterWidth + 1, y = 0; y < h; y++) {
        for (int x = 0; x < w; x++) {
            int r = 0;
            int g = 0;
            int b = 0;
            for (int filterIndex = 0, pixelIndex = y * width + x;
                    filterIndex < filter.length;
                    pixelIndex += pixelIndexOffset) {
                for (int fx = 0; fx < filterWidth; fx++, pixelIndex++, filterIndex++) {
                    int col = input[pixelIndex];
                    int factor = filter[filterIndex];

                    // sum up color channels seperately
                    r += ((col >>> 16) & 0xFF) * factor;
                    g += ((col >>> 8) & 0xFF) * factor;
                    b += (col & 0xFF) * factor;
                }
            }
            r /= sum;
            g /= sum;
            b /= sum;
            // combine channels with full opacity
            output[x + centerOffsetX + (y + centerOffsetY) * width] = (r << 16) | (g << 8) | b | 0xFF000000;
        }
    }

    BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    result.setRGB(0, 0, width, height, output, 0, width);
    return result;
}



     
      public BufferedImage changeSharpens(BufferedImage image, double val) {
                   
        int radius = (int)val;
        float[] data = new float[radius * radius];

        for (int i = 0; i < data.length; i++) {
            if(i==data.length/2){
                 data[i] = data.length;
            }else{
                 data[i] = -1;
            }
        }

        Kernel kernel = new Kernel(radius, radius, data);
  
       ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
//         ConvolveOp op = new ConvolveOp(kernel);

         BufferedImage sharpened = op.filter(image, null);
//       image = op.filter(image, null);

       return sharpened;
//         return image;
     }
     
     

    public BufferedImage dotsImage(BufferedImage image) {

        // get width and height 
        int width = image.getWidth();
        int height = image.getHeight();

        Color myWhite = new Color(255, 255, 255); // Color white
        Color dotColor = JColorChooser.showDialog(null, "Choose a color", myWhite);

//                int newColor3 = JColorChooser.showDialog(null, "Choose a color", myWhite).getRGB();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                int p = image.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                p = (a << 24) | (r << 16) | (g << 8) | b;

                if (x % 2 == 0 && y % 2 == 0) {

                    image.setRGB(x, y, dotColor.getRGB());

                }
            }
        }

        return image;
    }

    public void resizeSmaller(BufferedImage image, BufferedImage small, int n) {

        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width / n; x++) {
            for (int y = 0; y < height / n; y++) {

                int p = image.getRGB(x * n, y * n);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                p = (a << 24) | (r << 16) | (g << 8) | b;

                small.setRGB(x, y, p);

            }
        }

    }

    public void resizeBigger(BufferedImage image, BufferedImage big, int n) {

        for (int x = 0; x < image.getWidth() * n; x++) {
            for (int y = 0; y < image.getHeight() * n; y++) {

                int p = image.getRGB(x / n, y / n);

                big.setRGB(x, y, p);

            }
        }

    }

    public void grayScaling(BufferedImage image) {

        // get width and height 
        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int p = image.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                //za grayscale
                int avg = (r + g + b) / 3;

                // set new RGB 
                p = (a << 24) | (avg << 16) | (avg << 8) | avg;

                //moze i ovako
//                Color c = new Color(image.getRGB(x, y));
//                int red = (int) (c.getRed() * 0.299);
//                int green = (int) (c.getGreen() * 0.587);
//                int blue = (int) (c.getBlue() * 0.114);
//                Color newColor = new Color(red + green + blue,
//                        red + green + blue, red + green + blue);

                image.setRGB(x, y, p);

            }
        }

    }

    public BufferedImage convertToRed(BufferedImage image) {
        
       // get width and height 
                int width = image.getWidth();
                int height = image.getHeight();

                // convert to red image 
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        
                        int p = image.getRGB(x, y);
                        int a = (p >> 24) & 0xff;
                        int r = (p >> 16) & 0xff;
                        int g = (p >> 8) & 0xff;
                        int b = p & 0xff;
                       
                        p = (a << 24) | (r << 16) | (0 << 8) | 0;
                     
                        image.setRGB(x, y, p);
                    }
                }
                return image;
    }
    
    public BufferedImage convertToGreen(BufferedImage image) {
        
       // get width and height 
                int width = image.getWidth();
                int height = image.getHeight();

                // convert to red image 
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        
                        int p = image.getRGB(x, y);
                        int a = (p >> 24) & 0xff;
                        int r = (p >> 16) & 0xff;
                        int g = (p >> 8) & 0xff;
                        int b = p & 0xff;
                       
                        p = (a << 24) | (0 << 16) | (g << 8) | 0;
                     
                        image.setRGB(x, y, p);
                    }
                }
                return image;
    }
    
    
    public BufferedImage convertToBlue(BufferedImage image) {
        
       // get width and height 
                int width = image.getWidth();
                int height = image.getHeight();

                // convert to red image 
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        
                        int p = image.getRGB(x, y);
                        int a = (p >> 24) & 0xff ;
                        int r = (p >> 16) & 0xff;
                        int g = (p >> 8) & 0xff;
                        int b = p & 0xff;
                       
                        p = (a << 24) | (0 << 16) | (0 << 8) | b;
                     
                        image.setRGB(x, y, p);
                    }
                }
                return image;
    }
    
    
    
    
     public BufferedImage osvetljenostPixela(BufferedImage image) {
      // get width and height 
                int width = image.getWidth();
                int height = image.getHeight();

                Color myWhite = new Color(255, 255, 255); // Color white
                Color newColor3 = JColorChooser.showDialog(null, "Choose a color", myWhite);
                String osv = JOptionPane.showInputDialog("Unesite vrednost izmedju 0.0 i 1.0:");
                   float lite = Float.parseFloat(osv);
                 
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        
                        int p = image.getRGB(x, y);

                        int a = (p >> 24) & 0xff;
                        int red = Color.RED.getRGB();
                        int r = (p >> 16) & 0xff;
                        int g = (p >> 8) & 0xff;
                        int b = p & 0xff;
//                        p = (a << 24) | (r + newColor3.getRed() << 16) | (g + newColor3.getGreen() << 8) | b + newColor3.getBlue();
                       
                        float luminance = (r * 0.2126f + g * 0.7152f + b * 0.0722f) / 255;
                     
//                        if (luminance < 0.70f) {
                        if (luminance >= lite) {
//                            image.setRGB(x, y, p);
                                image.setRGB(x, y, newColor3.getRGB());
                        }

                    }
                }
                return image;
     }
     
     
  public BufferedImage targetRangePixels(BufferedImage image) {
     
      // get width and height 
                int width = image.getWidth();
                int height = image.getHeight();

                Color myWhite = new Color(255, 255, 255); // Color white
                Color newColor = JColorChooser.showDialog(null, "Choose a range color", myWhite);
                Color newColor2 = JColorChooser.showDialog(null, "Choose a range color", myWhite);

                int newColor3 = JColorChooser.showDialog(null, "Choose a replacement color", myWhite).getRGB();
//                Color newColor3 = JColorChooser.showDialog(null, "Choose a replacement color", myWhite);
                
//                ColorPicker colorPicker = new ColorPicker();
//               int a= colorPicker.getValue();
//              Color newColor = new Color(255, 255, 77);
//              Color newColor2 = new Color(179, 179, 0);
//              Color newColor = new Color(red2, green2, blue2);
//              Color newColor2 = new Color(red3, green3, blue3);

                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        
                        int p = image.getRGB(x, y);
                        int s = image.getRGB(x, y);
                        int a = (p >> 24) & 0xff;
                        int r = (p >> 16) & 0xff;
                        int g = (p >> 8) & 0xff;
                        int b = p & 0xff;
                        
        int ax = image.getColorModel().getAlpha(image.getRaster().
                getDataElements(x, y, null));
        int rx = image.getColorModel().getRed(image.getRaster().
                getDataElements(x, y, null));
        int gx = image.getColorModel().getGreen(image.getRaster().
                getDataElements(x, y, null));
        int bx = image.getColorModel().getBlue(image.getRaster().
                getDataElements(x, y, null));
//        rx *= 0.4;
//        gx *= 0.8;
//        bx *= 0.5;
//        ax *= 0.7;
//        rx *=(newColor3.getRed());
//        gx *= (newColor3.getGreen());
//        bx *= (newColor3.getBlue());
//        ax *= (newColor3.getAlpha());
//        image.setRGB(x, y, (ax << 24) | (rx << 16) | (gx << 8) | (bx));
                        
                        
//            int r = ((p >> 16) & 0xff + newColor3.getRed()) / 2;
//            int g = ((p >> 8) & 0xff + newColor3.getGreen()) / 2;
//            int b = ((p >> 0) & 0xff + newColor3.getBlue()) / 2;
//            int a = ((p >> 24)&0xff + newColor3.getAlpha())/2;
//              p = (a << 24) | (r << 16) | (g << 8) | b;

//        rx /= 0.4;
//        gx /= 0.8;
//        bx /= 0.5;
//        ax /= 0.7;
//  
                        if ((p <= newColor.getRGB() && p >= newColor2.getRGB()) || (p >= newColor.getRGB() && p <= newColor2.getRGB())) {
//                         image.setRGB(x, y, (ax << 24) | (rx << 16) | (gx << 8) | (bx));
                            image.setRGB(x, y, newColor3);
                        }
                    }
                }
                return image;
  }
     
    
  
    public BufferedImage sortImagePixels(BufferedImage image) {
                // get width and height 
                int width = image.getWidth();
                int height = image.getHeight();


                int[] nizPixela = putImagePixelsToArray(image);
                Arrays.sort(nizPixela);
//                quickSort(n,0,n.length-1);
//                mergeSort(n,0,n.length-1);
//                heapSort(n);

//                  sporo
//                for (int i = 0; i < nizPixela.length; i++) {
//                    for (int j = 0; j < nizPixela.length; j++) {
//                         if(nizPixela[i] > nizPixela[j])
//                            {
//                                int temp = nizPixela[i];
//                                nizPixela[i] = nizPixela[j];
//                                nizPixela[j] = temp;
//                                
////                                int temp = n[j];
////                                n[j] = n[i];
////                                n[i] = temp;
//                            }
//                    }
//                }

                
                //postavljla pixele iz sortiranog niza u sliku.
                int t = 0;
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        // int p = srt.getRGB(x, y);

                        image.setRGB(x, y, nizPixela[t++]);
          
                    }
                }
            return image;
                
    }
    
    
    
    public BufferedImage reversingColors(BufferedImage image) { 
                // get width and height 
                int width = image.getWidth();
                int height = image.getHeight();


                for (int x = 0; x < width  ; x++) {
                   for (int y = 0; y < height; y++) {
                       
                        int p = image.getRGB(x, y);
                        
                        int a = (p >> 24) & 0xff;
                        int r = (p >> 16) & 0xff;
                        int g = (p >> 8) & 0xff;
                        int b = p & 0xff;
                               
                         r = 255 - r; 
                         g = 255 - g; 
                         b = 255 - b; 
                         
                        //set new RGB value 
                         p = (a<<24) | (r<<16) | (g<<8) | b; 

                             image.setRGB(x, y, p);
                    }
                }
                return image;
    
    }
    
    
    
     public BufferedImage rotateY(BufferedImage image) { 
        // get width and height 
                int width = image.getWidth();
                int height = image.getHeight();
            
                for (int x = 0, x2 = width-1; x < width / 2 ; x++, x2--) {
                   for (int y = 0,y2 = height-1; y < height ; y++, y2--) {
                       
                        int p = image.getRGB(x, y);
                       
                        //    mirror image okretanje slike po x osi
                        int p2 = image.getRGB(x2, y);
                        
//                        za obrtanje slike  po y osi
//                        int p2 = mirror.getRGB(x2, y2);

                        int a = (p >> 24) & 0xff;
                        int r = (p >> 16) & 0xff;
                        int g = (p >> 8) & 0xff;
                        int b = p & 0xff;
                        
                        int a2 = (p2 >> 24) & 0xff;
                        int r2 = (p2 >> 16) & 0xff;
                        int g2 = (p2 >> 8) & 0xff;
                        int b2 = p2 & 0xff;

                        //set new RGB value 
                         p = (a<<24) | (r<<16) | (g<<8) | b; 
                         p2 = (a2<<24) | (r2<<16) | (g2<<8) | b2; 
                                           

//                          mirror image okretanje po x osi
                             image.setRGB(x2, y, p);
                            image.setRGB(x, y, p2);
                            
                            //rotira sliku za 180 stepeni naopacke po y osi
//                            image.setRGB(x2, y2, p);
//                           image.setRGB(x, y, p2);
                           
                            //kopira gornju polovinu slike u donju
//                            image.setRGB(x, y2, p);
//                            image.setRGB(x2, y2, p2);

                            //kopira desnu polovinu slike u levu
//                            image.setRGB(x, y, p2);
//                        //    image.setRGB(x, y2, p);

                    }
                }
      return image;          
     }
     
     
     
  // ***************************** odavde je edge detection *************************************************************
         
    public static final String VERTICAL_FILTER = "Vertical Filter";
    public static final String HORIZONTAL_FILTER = "Horizontal Filter";
    public static final String VERTICAL_AND_HORIZONTAL_COMBINED = "Mix Vertical and Horizontal";

    public static final String SOBEL_FILTER_VERTICAL = "Sobel Vertical Filter";
    public static final String SOBEL_FILTER_HORIZONTAL = "Sobel Horizontal Filter";

    public static final String SCHARR_FILTER_VERTICAL = "Scharr Vertical Filter";
    public static final String SCHARR_FILTER_HORIZONTAL = "Scharr Horizontal Filter";

    private static final double[][] FILTER_VERTICAL = {{1, 0, -1}, {1, 0, -1}, {1, 0, -1}};
    private static final double[][] FILTER_HORIZONTAL = {{1, 1, 1}, {0, 0, 0}, {-1, -1, -1}};
    private static final double[][] COMBINED_VERTICAL_AND_HORIZONTAL = {{2, 1, 0}, {1, 0, -1}, {0, -1, -2}}; // sabran vertical and horizontal
//    private static final double[][] COMBINED_VERTICAL_AND_HORIZONTAL = {{0, -1, -2}, {1, 0, -1}, {2, 1, 0}}; // od vertical oduzet horizontal
//    private static final double[][] COMBINED_VERTICAL_AND_HORIZONTAL = {{0, 1, 2}, {-1, 0, 1}, {-2, -1, 0}};  // od horizontal oduzet verical

    private static final double[][] FILTER_SOBEL_V = {{1, 0, -1}, {2, 0, -2}, {1, 0, -1}};
    private static final double[][] FILTER_SOBEL_H = {{1, 2, 1}, {0, 0, 0}, {-1, -2, -1}};

    private static final double[][] FILTER_SCHARR_V = {{3, 0, -3}, {10, 0, -10}, {3, 0, -3}};
    private static final double[][] FILTER_SCHARR_H = {{3, 10, 3}, {0, 0, 0}, {-3, -10, -3}};
    
     private  HashMap<String, double[][]> filterMap;
     
     
     // ova metoda je glavna i ona se pziva
        public BufferedImage detectEdges(BufferedImage bufferedImage, String selectedFilter) throws IOException {
        double[][][] image = transformImageToArray(bufferedImage);
        filterMap = buildFilterMap();
        double[][] filter = filterMap.get(selectedFilter);
//        double[][] filter = FILTER_VERTICAL;
        double[][] convolvedPixels = applyConvolution(bufferedImage.getWidth(),
                bufferedImage.getHeight(), image, filter);
        return createImageFromConvolutionMatrix(bufferedImage, convolvedPixels);
    }

    private double[][][] transformImageToArray(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        double[][][] image = new double[3][height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color = new Color(bufferedImage.getRGB(j, i));
                image[0][i][j] = color.getRed();
                image[1][i][j] = color.getGreen();
                image[2][i][j] = color.getBlue();
            }
        }
        return image;
    }

    private double[][] applyConvolution(int width, int height, double[][][] image, double[][] filter) {
        Convolution convolution = new Convolution();
        double[][] redConv = convolution.convolutionType2(image[0], height, width, filter, 3, 3, 1);
        double[][] greenConv = convolution.convolutionType2(image[1], height, width, filter, 3, 3, 1);
        double[][] blueConv = convolution.convolutionType2(image[2], height, width, filter, 3, 3, 1);
        double[][] finalConv = new double[redConv.length][redConv[0].length];
        for (int i = 0; i < redConv.length; i++) {
            for (int j = 0; j < redConv[i].length; j++) {
                finalConv[i][j] = redConv[i][j] + greenConv[i][j] + blueConv[i][j];
            }
        }
        return finalConv;
    }

    private BufferedImage createImageFromConvolutionMatrix(BufferedImage originalImage, double[][] imageRGB) throws IOException {
        BufferedImage writeBackImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < imageRGB.length; i++) {
            for (int j = 0; j < imageRGB[i].length; j++) {
                Color color = new Color(fixOutOfRangeRGBValues(imageRGB[i][j]),
                        fixOutOfRangeRGBValues(imageRGB[i][j]),
                        fixOutOfRangeRGBValues(imageRGB[i][j]));
                writeBackImage.setRGB(j, i, color.getRGB());
            }
        }
//        File outputFile = new File("EdgeDetection/edgesTmp.png");
//        ImageIO.write(writeBackImage, "png", outputFile);
        return writeBackImage;
    }


    private HashMap<String, double[][]> buildFilterMap() {
        HashMap<String, double[][]> filterMap;
        filterMap = new HashMap<>();
        filterMap.put(VERTICAL_FILTER, FILTER_VERTICAL);
        filterMap.put(HORIZONTAL_FILTER, FILTER_HORIZONTAL);
        filterMap.put(VERTICAL_AND_HORIZONTAL_COMBINED, COMBINED_VERTICAL_AND_HORIZONTAL);
        
        filterMap.put(SOBEL_FILTER_VERTICAL, FILTER_SOBEL_V);
        filterMap.put(SOBEL_FILTER_HORIZONTAL, FILTER_SOBEL_H);

        filterMap.put(SCHARR_FILTER_VERTICAL, FILTER_SCHARR_V);
        filterMap.put(SCHARR_FILTER_HORIZONTAL, FILTER_SCHARR_H);
       
        return filterMap;
    }

 
 //*********************************  dovde je edge detection  *****************************************************
 
  // for radio buttnons   
  public BufferedImage convertToRedGreenBlue(BufferedImage image, String s) { 
        // get width and height 
                int width = image.getWidth();
                int height = image.getHeight();

                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        int p = image.getRGB(x, y);
                        int a = (p >> 24) & 0xff;
                        int r = (p >> 16) & 0xff;
                        int g = (p >> 8) & 0xff;
                        int b = p & 0xff;
                      
                         if(s.equals("R")){
                                p = (a << 24) | (r << 16) | (0 << 8) | 0;
                            }else if(s.equals("G")){
                                p = (a << 24) | (0 << 16) | (g << 8) | 0;
                            }else{ 
                                p = (a << 24) | (0 << 16) | (0 << 8) | b;
                            }    
 
                        image.setRGB(x, y, p);

                    }
                }
                return image;
  }
    
    public int checkColorRange(int newColor) {
        if (newColor > 255) {
            newColor = 255;
        } else if (newColor < 0) {
            newColor = 0;
        }
        return newColor;
    }
    
    
    public int fixOutOfRangeRGBValues(double value) {
        if (value < 0.0) {
            value = -value;
        }
        if (value > 255) {
            return 255;
        } else {
        return (int) value;
        }
       }
    
    
     //konvertovanje buffered image  to imageview
    public Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
    }

    //ubacivanje pixela slike u jednodimenzionalni niz
    public int[] putImagePixelsToArray(BufferedImage image) {

        int[] pixeli = new int[image.getWidth() * image.getHeight()];
        int k = 0;
        for (int i = 0; i <  image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                pixeli[k++] = image.getRGB(i, j);
            }
        }

        return pixeli;

    }

    //   ******************    Quick Sort      *********************
    /* This function takes last element as pivot, 
       places the pivot element at its correct 
       position in sorted array, and places all 
       smaller (smaller than pivot) to left of 
       pivot and all greater elements to right 
       of pivot */
    public int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element 
        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot 
            if (arr[j] < pivot) {
                i++;

                // swap arr[i] and arr[j] 
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    /* The main function that implements QuickSort() 
      arr[] --> Array to be sorted, 
      low  --> Starting index, 
      high  --> Ending index */
    public void quickSort(int arr[], int low, int high) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before 
            // partition and after partition 
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

     //   ******************    Merge Sort      *********************
    // Merges two subarrays of arr[]. 
    // First subarray is arr[l..m] 
    // Second subarray is arr[m+1..r] 
    public void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
        }

        /* Merge the temp arrays */
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0;

        // Initial index of merged subarry array 
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using merge()
    public void mergeSort(int arr[], int l, int r) {
        if (l < r) {
            // Find the middle point 
            int m = (l + r) / 2;

            // Sort first and second halves 
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves 
            merge(arr, l, m, r);
        }
    }
    

  //   ******************    Heap Sort      *********************
    public void heapSort(int arr[]) {
        int n = arr.length;

        // Build heap (rearrange array) 
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // One by one extract an element from heap 
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end 
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap 
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is 
    // an index in arr[]. n is size of heap 
    public void heapify(int arr[], int n, int i) {
        int largest = i;  // Initialize largest as root 
        int l = 2 * i + 1;  // left = 2*i + 1 
        int r = 2 * i + 2;  // right = 2*i + 2 

        // If left child is larger than root 
        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }

        // If right child is larger than largest so far 
        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }

        // If largest is not root 
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree 
            heapify(arr, n, largest);
        }
    }

    
    
    
}
