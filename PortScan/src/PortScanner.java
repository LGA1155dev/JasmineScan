import java.net.InetSocketAddress;
import java.net.Socket;
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


public class PortScanner {

    public static void main(final String... args)

            throws InterruptedException, ExecutionException {

                        final ExecutorService es = Executors.newCachedThreadPool();
                        System.out.print("Ip que deseja escanear:  ");
                        Scanner inputScanner = new Scanner(System.in);
                        final String ip = inputScanner.nextLine();
                        LocalDate today = LocalDate.now();
                        int year = today.getYear();
                        int month = today.getDayOfMonth();
                        int day = today.getMonthValue();

                        System.out.println("JasmineScan está começando a  escanear... ( https://jasminescan.org )  "  + year + "/0" + day + "/0" + month );


                        final int timeout = 200;
                        final List<Future<ScanResult>> futures = new ArrayList<>();

                        for (int port = 1;  port <= 65535; port++){
                                        futures.add(portIsOpen(es, ip, port, timeout));
                        }

                        es.shutdown();
                        es.awaitTermination(
                                200,
                                TimeUnit.MILLISECONDS
                        );

                        int openPorts = 0;

                        for (final Future<ScanResult> f : futures){

                            ScanResult result = f.get();
                            if (result.isOpen()){

                                openPorts++;
                                System.out.println(f.get().getPort());

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



