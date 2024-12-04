import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CompressionApp extends JFrame
{
    private JTextField filePathField;
    private JTable table;
    private String[] columnNames = {"Algorithm", "Compression Ratio", "Compression Speed", "Decompression Speed", "Use Case", "Actions"};
    private Object[][] data = {
        {"ZIP", "Medium", "Fast", "Fast", "General-purpose", null},
        {"7z", "High", "Medium", "Medium", "Large files, archives", null},
        {"zstd", "Medium-High", "Very Fast", "Very Fast", "Real-time compression", null},
        {"Gzip", "Medium", "Fast", "Fast", "Web assets, Unix systems", null},
        {"Bzip2", "High", "Slow", "Medium", "Large files, archival", null},
        {"Brotli", "High", "Medium", "Fast", "Web-related compression", null},
        {"xz", "Very High", "Slow", "Medium", "Long-term archival", null},
    };

    public CompressionApp()
    {
        setTitle("Compression/Decompression App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // File Selector
        JPanel filePanel = new JPanel(new BorderLayout());
        filePathField = new JTextField();
        JButton browseButton = new JButton("Browse");
        browseButton.addActionListener(new BrowseButtonListener());
        filePanel.add(filePathField, BorderLayout.CENTER);
        filePanel.add(browseButton, BorderLayout.EAST);

        // Table
        DefaultTableModel model = new DefaultTableModel(data, columnNames)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                // Only allow button cells to be editable
                return column == 5;
            }
        };
        table = new JTable(model);

        // Add buttons to table
        for (int i = 0; i < table.getRowCount(); i++)
        {
            JPanel actionPanel = new JPanel();
            JButton compressButton = new JButton("Compress");
            compressButton.addActionListener(new CompressButtonListener(i));
            JButton decompressButton = new JButton("Decompress");
            decompressButton.addActionListener(new DecompressButtonListener(i));
            actionPanel.add(compressButton);
            actionPanel.add(decompressButton);
            table.setValueAt(actionPanel, i, 5);
        }

        JScrollPane scrollPane = new JScrollPane(table);

        // Layout
        add(filePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private class BrowseButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(CompressionApp.this);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                File selectedFile = fileChooser.getSelectedFile();
                filePathField.setText(selectedFile.getAbsolutePath());
            }
        }
    }

    private class CompressButtonListener implements ActionListener
    {
        private final int rowIndex;

        public CompressButtonListener(int rowIndex)
        {
            this.rowIndex = rowIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            String filePath = filePathField.getText();
            if (filePath.isEmpty())
            {
                JOptionPane.showMessageDialog(CompressionApp.this, "Please select a file!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String algorithm = (String) table.getValueAt(rowIndex, 0);
            JOptionPane.showMessageDialog(CompressionApp.this, "Compressing with " + algorithm + " is not implemented yet.");
        }
    }

    private class DecompressButtonListener implements ActionListener
    {
        private final int rowIndex;

        public DecompressButtonListener(int rowIndex)
        {
            this.rowIndex = rowIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            String filePath = filePathField.getText();
            if (filePath.isEmpty())
            {
                JOptionPane.showMessageDialog(CompressionApp.this, "Please select a file!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String algorithm = (String) table.getValueAt(rowIndex, 0);
            JOptionPane.showMessageDialog(CompressionApp.this, "Decompressing with " + algorithm + " is not implemented yet.");
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            CompressionApp app = new CompressionApp();
            app.setVisible(true);
        });
    }
}
