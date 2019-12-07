package Unity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;



public class ReceiptPrinting 
{
	
	//private static final String ESC = Character.toString((char)27);
	private static final String ESC = "\u001B";
	private static final String GS = "\u001D";
	private static final String RESET = ESC + "@";
	private static final String DOUBLEON = GS + "!" + "\u0011";
	private static final String DOUBLEOFF = GS + "!" + "\0";
	private static final String BOLDON = ESC + "E" + "\u0001";
	private static final String BOLDOFF = ESC + "E" + "\0";
	   String test2String = GS + "!" + "\0";
	private int rollWidth = 80;
	
	public void print(String toPrintIn)
	{
	   try {
       	String toPrint = toPrintIn;
      
           //String printerName = Main.MainInstance.getMain().ge
           InputStream is = new ByteArrayInputStream(toPrint.getBytes());
           DocFlavor flavor =  DocFlavor.INPUT_STREAM.AUTOSENSE   ;
           HashAttributeSet attributeSet = new HashAttributeSet();
           String printerName = Main.MainInstance.getConfig().getReceiptPrinter();
           attributeSet.add(new PrinterName(printerName, null));
           PrintService[] services = PrintServiceLookup.lookupPrintServices(null,  attributeSet);
           DocPrintJob job = services[0].createPrintJob();
           Doc doc= new SimpleDoc(is, flavor, null);
           PrintJobWatcher pjDone = new PrintJobWatcher(job);
           job.print(doc, null);
           pjDone.waitForDone();
           is.close();
       } catch (PrintException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   static class PrintJobWatcher {
       // true iff it is safe to close the print job's input stream
       boolean done = false;

       PrintJobWatcher(DocPrintJob job) {
           // Add a listener to the print job
           job.addPrintJobListener(new PrintJobAdapter() {
               public void printJobCanceled(PrintJobEvent pje) {
            	   System.out.println("I was canceled");
                   allDone();
               }
               public void printJobCompleted(PrintJobEvent pje) {
                   allDone();
                  
               }
               public void printJobFailed(PrintJobEvent pje) {
            	   System.out.println("I failed");
                   allDone();
               }
               public void printJobNoMoreEvents(PrintJobEvent pje) {
                   allDone();
               }
               void allDone() {
                   synchronized (PrintJobWatcher.this) {
                       done = true;
                       PrintJobWatcher.this.notify();
                   }
               }
           });
       }
       public synchronized void waitForDone() {
           try {
               while (!done) {
                   wait();
               }
           } catch (InterruptedException e) {
           }
       }
   }
   /**
    * linear algebra in a nutshell
    * @return
    */
   public String verticalLine()
   {
	   String line = "";
	   for (int i = 0; i < rollWidth / 1.91; i++)
	   {
		   line += "-";
	   }
	   
	   return line;
	   
   }
   /**
    * Tabs to the center
    * @return
    */
   public String tab(int n)
   {
	   String tab = "";
	   for (int i = 0; i < n; i++ )
	   {
		   tab += "\u0009";
	   }
	   return tab;
   }
   
   public String cutHere()
   {
       String cut = "";
       cut = ESC + "@";
       cut += GS + "V" + (char)1;
       return cut;
   }
   
   public String newLine(int x)
   {
	   String newLine = "";
	   for (int i = 0; i < x; i++)
	   {
		   newLine += Character.toString((char)10);
	   }
	   return newLine;
   }
   
   public String attemptBeep()
   {
	   String beep = ESC + "";
	   return beep;
   }
   
   public String endOfReceipt()
   {
		String end = "";
		String tempString = "CUSTOMER SIGNATURE";
		String tempUser = "CLERK: " + Main.MainInstance.getCurrentUser();
		end += newLine(1);
		end += verticalLine();
		end += String.format("%1$" + padding(tempUser) + "s" , tempUser + newLine(2));
		end += verticalLine();
		end += String.format("%1$" + padding(tempString) + "s" , tempString);
		end += newLine(6);
		end += cutHere();
		return end;
   }
   
   public int padding(String stringIn) 
   {
	   String tempString = stringIn;
	   //TODO ANGIE CONFIG THIS ONE (/2)
	   double widthConversion = rollWidth / 1.90; // 1.90 works for 80, unsure for < 50
	   int padding = (int) (widthConversion / 2);
	   int startPadding = tempString.length();  
	   padding = (padding + (startPadding / 2));
	   if(stringIn.equals("UNITY"))
	   {
		   return (int)(padding / 1.75); // LARGER TEXT LESS ADJUSTING
	   }
	   return padding;
   }
   /**
    * its just so dynamic
    * @return
    */
   public String createStartOfPrinterString()
   {
	   Date dt = new Date();
	   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY hh:mm:ss a");
	   String dateString = sdf.format(dt);
	   String unity = "UNITY";
	   String serverName = "Downstairs";
	   String address = "1406 Fort Crook Rd S"; 
	   String location = "Bellevue, NE 68005";
	   String phoneNumber = "402-731-4138";
	   String receipt = "CASHOUT RECEIPT";
	   
	   String start = String.format(RESET + DOUBLEON + BOLDON + 
	   "%1$" + padding(unity) + "s" + newLine(2) + DOUBLEOFF + BOLDOFF
	   + "%2$" + padding(serverName) + "s" + newLine(1) 
	   + "%3$" + padding(address) + "s" + newLine(1)
	   + "%4$" + padding(location) + "s" + newLine(1)
	   + "%5$" + padding(phoneNumber) + "s" + newLine(2)
	   + "%6$" + padding(receipt) + "s" + newLine(2)
	   + "%7$" + padding(dateString) + "s" + newLine(2)
	   + verticalLine() + newLine(2), 
	   unity, serverName, address, location, phoneNumber, receipt, dateString);
	   return start;
   }
}


