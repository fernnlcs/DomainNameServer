import main.core.DNSSimulator;
import main.utils.Logger;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Logger logger = new Logger();
        DNSSimulator simulator = new DNSSimulator(logger);
        simulator.interact(new Scanner(System.in));
    }
}
