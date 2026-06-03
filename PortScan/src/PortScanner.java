import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.*;
import  java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.time.LocalDate;
import java.time.LocalDateTime;



public class PortScanner {

    public static void main(final String... args)

            throws Exception {

                        final ExecutorService es = Executors.newCachedThreadPool();
                        System.out.print("Ip que deseja escanear:  ");
                        Scanner inputScanner = new Scanner(System.in);
                        final String ip = inputScanner.nextLine();
                        LocalDate today = LocalDate.now();
                        int year = today.getYear();
                        int month = today.getDayOfMonth();
                        int day = today.getMonthValue();
                        LocalDateTime time = LocalDateTime.now();
                        int hour = time.getHour();
                        int minute = time.getMinute();
                        int second = time.getSecond();
                        String state;

                        System.out.println();
                        System.out.println(" ");
                        AsciiArt.printBanner();
                        System.out.println();
                        System.out.println(" ");
                        System.out.println("JasmineScan está começando a  escanear... ( https://jasminescan.org )  "  + year + "/0" + day + "/0" +  month + " - " + hour +":"+ minute + ":"+ second);
                        System.out.println();
                        System.out.println(" ");


                        final int timeout = 200;
                        final List<Future<ScanResult>> futures = new ArrayList<>();

                        for (int port = 1;  port <= 65535; port++){
                                        futures.add(portIsOpen(es, ip, port, timeout));
                        }

                        es.shutdown();
                        es.awaitTermination(
                                5,
                                TimeUnit.SECONDS
                        );


                        int openPorts = 0;

                        for (final Future<ScanResult> f : futures){

                            ScanResult result = f.get();

                            if (result.isOpen()){
                                state = "open";

                                Map<Integer, String> services = Map.of(
                                        80, "HTTP",
                                        443, "HTTPS",
                                        53, "DNS",
                                        22, "SSH",
                                        23, "Telnet",
                                        21, "FTP",
                                        20, "FTP",
                                        110, "POP3",
                                        143, "IMAP",
                                        25, "SMTP"

                                );

                                openPorts++;
                                System.out.println();
                                System.out.println(" ");
                                System.out.println("PORTA |   ESTADO   | SERVIÇO    ");
                                System.out.println(" ");
                                System.out.println(f.get().getPort() + "/tcp|    " + state +"    | "+ services.get(result.getPort()));
                                System.out.println();
                                System.out.println(" ");
                                System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=" );
                                System.out.println();
                                System.out.println(" ");

                            } else{
                                state = "close";
                            }
                        }
        System.out.println("Existem: " + openPorts + " portas abertas na host " + ip + " (o tempo de espera foi: " + timeout + " ms)");

    }
    public static Future<ScanResult> portIsOpen(final ExecutorService es, final String ip, final int port, final int timeout ) {

        return es.submit(new Callable<ScanResult>() {
                @Override
                public ScanResult call(){
                            try{
                                Socket socket = new Socket();
                                socket.connect(new InetSocketAddress(ip, port), timeout);
                                socket.close();
                                return new ScanResult(port, true);
                            } catch (Exception ex){
                                return new ScanResult(port, false);
                            }
                }
        });
    }
    public static class ScanResult{
        private int port;
        private boolean isOpen;

        public ScanResult(int port, boolean isOpen){
            super();
            this.port = port;
            this.isOpen = isOpen;
        }


        public int getPort() {
            return this.port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public boolean isOpen() {
            return this.isOpen;
        }

        public void setOpen(boolean open) {
            this.isOpen = open;
        }
    }
}



