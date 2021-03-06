package pl.jrkn87.junit.order;

import pl.jrkn87.junit.order.Order;

import java.io.*;

public class OrderBackup {
    private Writer writer;

    public void createFile() throws FileNotFoundException {
        File file = new File("orderBackup.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        writer = new BufferedWriter(outputStreamWriter);
    }

    public void backupOrder(Order order) throws IOException {
        writer.append(order.toString());
    }

    public void closeFile() throws IOException {
        writer.close();
    }

    public Writer getWriter() {
        return writer;
    }
}
