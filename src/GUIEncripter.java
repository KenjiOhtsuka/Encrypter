import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Dimension;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GUIEncripter {

  private JFrame frmXor;
  private JTextField outFileName;
  private JTextField inFileName;
  private JTable keyTable;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          GUIEncripter window = new GUIEncripter();
          window.frmXor.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public GUIEncripter() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmXor = new JFrame();
    frmXor.setTitle("XOR 暗号化/復号");
    frmXor.setBounds(100, 100, 450, 300);
    frmXor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel panel = new JPanel();
    frmXor.getContentPane().add(panel, BorderLayout.CENTER);
    SpringLayout sl_panel = new SpringLayout();
    panel.setLayout(sl_panel);
    
    JLabel label = new JLabel("出力ファイル");
    sl_panel.putConstraint(SpringLayout.NORTH, label, 34, SpringLayout.NORTH, panel);
    sl_panel.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, panel);
    sl_panel.putConstraint(SpringLayout.EAST, label, 97, SpringLayout.WEST, panel);
    panel.add(label);
    
    outFileName = new JTextField();
    sl_panel.putConstraint(SpringLayout.WEST, outFileName, 0, SpringLayout.EAST, label);
    sl_panel.putConstraint(SpringLayout.EAST, outFileName, -20, SpringLayout.EAST, panel);
    outFileName.setAlignmentX(Component.RIGHT_ALIGNMENT);
    outFileName.setAlignmentY(Component.TOP_ALIGNMENT);
    outFileName.setName("");
    outFileName.setColumns(10);
    panel.add(outFileName);
    
    inFileName = new JTextField();
    inFileName.setAlignmentX(Component.RIGHT_ALIGNMENT);
    sl_panel.putConstraint(SpringLayout.NORTH, outFileName, 6, SpringLayout.SOUTH, inFileName);
    sl_panel.putConstraint(SpringLayout.WEST, inFileName, 97, SpringLayout.WEST, panel);
    sl_panel.putConstraint(SpringLayout.EAST, inFileName, -20, SpringLayout.EAST, panel);
    inFileName.setAlignmentY(Component.TOP_ALIGNMENT);
    sl_panel.putConstraint(SpringLayout.SOUTH, inFileName, 26, SpringLayout.NORTH, panel);
    sl_panel.putConstraint(SpringLayout.NORTH, inFileName, 7, SpringLayout.NORTH, panel);
    inFileName.setColumns(10);
    panel.add(inFileName);
    
    JLabel label_1 = new JLabel("対象ファイル");
    sl_panel.putConstraint(SpringLayout.NORTH, label_1, 9, SpringLayout.NORTH, panel);
    sl_panel.putConstraint(SpringLayout.WEST, label_1, 10, SpringLayout.WEST, panel);
    sl_panel.putConstraint(SpringLayout.EAST, label_1, 0, SpringLayout.WEST, inFileName);
    panel.add(label_1);
    
    JButton browseInFileButton = new JButton("...");
    sl_panel.putConstraint(SpringLayout.NORTH, browseInFileButton, 0, SpringLayout.NORTH, inFileName);
    sl_panel.putConstraint(SpringLayout.EAST, inFileName, -6, SpringLayout.WEST, browseInFileButton);
    browseInFileButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String fileName = inFileName.getText();
        if (fileName.isEmpty()){
          fileName = outFileName.getText();
        }
        javax.swing.JFileChooser fc =
          new javax.swing.JFileChooser(fileName);
        if (fc.showOpenDialog(null) == javax.swing.JFileChooser.APPROVE_OPTION) {
          inFileName.setText(fc.getSelectedFile().getAbsolutePath());
        }
      }
    });
    browseInFileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    browseInFileButton.setAlignmentY(Component.TOP_ALIGNMENT);
    sl_panel.putConstraint(SpringLayout.WEST, browseInFileButton, -28, SpringLayout.EAST, panel);
    sl_panel.putConstraint(SpringLayout.EAST, browseInFileButton, -10, SpringLayout.EAST, panel);
    browseInFileButton.setSize(new Dimension(18, 18));
    panel.add(browseInFileButton);
    
    JButton browseOutFileButton = new JButton("...");
    sl_panel.putConstraint(SpringLayout.SOUTH, browseInFileButton, 0, SpringLayout.SOUTH, inFileName);
    sl_panel.putConstraint(SpringLayout.EAST, outFileName, -6, SpringLayout.WEST, browseOutFileButton);
    sl_panel.putConstraint(SpringLayout.SOUTH, browseOutFileButton, 0, SpringLayout.SOUTH, outFileName);
    browseOutFileButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String fileName = outFileName.getText();
        if (fileName.isEmpty()) {
          fileName = inFileName.getText();
        }
        javax.swing.JFileChooser fc =
          new javax.swing.JFileChooser(fileName);
        if (fc.showSaveDialog(null) == javax.swing.JFileChooser.APPROVE_OPTION) {
          outFileName.setText(fc.getSelectedFile().getAbsolutePath());
        }
      }
    });
    browseOutFileButton.setAlignmentY(Component.TOP_ALIGNMENT);
    browseOutFileButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
    sl_panel.putConstraint(SpringLayout.NORTH, browseOutFileButton, 32, SpringLayout.NORTH, panel);
    sl_panel.putConstraint(SpringLayout.WEST, browseOutFileButton, -28, SpringLayout.EAST, panel);
    sl_panel.putConstraint(SpringLayout.EAST, browseOutFileButton, -10, SpringLayout.EAST, panel);
    browseOutFileButton.setSize(new Dimension(18, 18));
    panel.add(browseOutFileButton);
    
    JScrollPane scrollPane = new JScrollPane();
    sl_panel.putConstraint(SpringLayout.NORTH, scrollPane, 4, SpringLayout.SOUTH, outFileName);
    sl_panel.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, panel);
    sl_panel.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, browseInFileButton);
    panel.add(scrollPane);
    
    keyTable = new JTable();
    scrollPane.setViewportView(keyTable);
    sl_panel.putConstraint(SpringLayout.NORTH, keyTable, 11, SpringLayout.SOUTH, outFileName);
    sl_panel.putConstraint(SpringLayout.WEST, keyTable, 10, SpringLayout.WEST, panel);
    sl_panel.putConstraint(SpringLayout.SOUTH, keyTable, -128, SpringLayout.SOUTH, panel);
    sl_panel.putConstraint(SpringLayout.EAST, keyTable, 0, SpringLayout.EAST, browseInFileButton);
    keyTable.setModel(new DefaultTableModel(
      new Object[][] {
        {null},
      },
      new String[] {
        "\u6697\u53F7\u5316\u30AD\u30FC"
      }
    ) {
      Class[] columnTypes = new Class[] {
        String.class
      };
      public Class getColumnClass(int columnIndex) {
        return columnTypes[columnIndex];
      }
    });
    keyTable.getColumnModel().getColumn(0).setResizable(false);
    keyTable.setBorder(new LineBorder(new Color(0, 0, 0)));
    
    JButton encryptButton = new JButton("暗号化/復号");
    encryptButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // check file names
        java.io.File file;
        file = new java.io.File(inFileName.getText());
        if (!file.exists() || !file.isFile()) {
          javax.swing.JOptionPane.showMessageDialog(
              null, "暗号化/復号ファイル が存在しません。", "エラー",
              javax.swing.JOptionPane.ERROR_MESSAGE);
          return;
        }
        file = new java.io.File(outFileName.getText());
        if (file.exists()) {
          int userRes = javax.swing.JOptionPane.showConfirmDialog(
              null, "出力ファイル は既に存在します。上書きしてもよろしいですか？", 
              "確認", javax.swing.JOptionPane.YES_NO_OPTION);
          if (userRes != javax.swing.JOptionPane.YES_OPTION) {
            return;
          }
        }
        if (inFileName.getText().equalsIgnoreCase(outFileName.getText())) {
          javax.swing.JOptionPane.showMessageDialog(
              null, "暗号化/復号ファイル と 出力ファイル には 別のファイルを指定してください。\n大文字と小文字は区別されません。",
                  "エラー", javax.swing.JOptionPane.ERROR_MESSAGE);
          return;
        }
        // check key data
        java.util.LinkedList ls = new java.util.LinkedList();
        String key;
        DefaultTableModel model = (DefaultTableModel)keyTable.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
          if (!(key = model.getValueAt(i, 0).toString()).isEmpty()) {
            ls.add(key);
          }
        }
        if (ls.size() == 0) {
          javax.swing.JOptionPane.showMessageDialog(
              null, "暗号化キーを設定してください。",
                  "エラー", javax.swing.JOptionPane.ERROR_MESSAGE);
          return;
        }
        try {
          ls.addFirst(outFileName.getText());
          ls.addFirst(inFileName.getText());
          Encrypter.main((String[])(ls.toArray(new String[0])));
        } catch (Exception ex) {
          javax.swing.JOptionPane.showMessageDialog(
              null, "エラーが発生しました。",
                  "エラー", javax.swing.JOptionPane.ERROR_MESSAGE);
          return;
        }
        javax.swing.JOptionPane.showMessageDialog(
            null, "暗号化/復号処理が終了しました。",
                "通知", javax.swing.JOptionPane.INFORMATION_MESSAGE);
      }
    });
    sl_panel.putConstraint(SpringLayout.SOUTH, scrollPane, -6, SpringLayout.NORTH, encryptButton);
    sl_panel.putConstraint(SpringLayout.WEST, encryptButton, -110, SpringLayout.EAST, browseInFileButton);
    encryptButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    encryptButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
    sl_panel.putConstraint(SpringLayout.NORTH, encryptButton, -35, SpringLayout.SOUTH, panel);
    sl_panel.putConstraint(SpringLayout.SOUTH, encryptButton, -10, SpringLayout.SOUTH, panel);
    sl_panel.putConstraint(SpringLayout.EAST, encryptButton, 0, SpringLayout.EAST, browseInFileButton);
    panel.add(encryptButton);
    
    JButton addKeyButton = new JButton("追加");
    sl_panel.putConstraint(SpringLayout.WEST, addKeyButton, 10, SpringLayout.WEST, panel);
    addKeyButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel)keyTable.getModel();
        model.addRow(new String[] {""});
      }
    });
    addKeyButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    sl_panel.putConstraint(SpringLayout.NORTH, addKeyButton, -35, SpringLayout.SOUTH, panel);
    sl_panel.putConstraint(SpringLayout.SOUTH, addKeyButton, -10, SpringLayout.SOUTH, panel);
    panel.add(addKeyButton);
    
    JButton deleteKeyButton = new JButton("削除");
    sl_panel.putConstraint(SpringLayout.WEST, deleteKeyButton, 76, SpringLayout.WEST, panel);
    sl_panel.putConstraint(SpringLayout.EAST, addKeyButton, -6, SpringLayout.WEST, deleteKeyButton);
    deleteKeyButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int selectedRecordIndex = keyTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)keyTable.getModel();
        if (selectedRecordIndex < 0 ||
            selectedRecordIndex >= model.getRowCount()) {
          javax.swing.JOptionPane.showMessageDialog(
              null, "削除対象のキーを選択してください。", "エラー",
              javax.swing.JOptionPane.OK_OPTION);
          return;
        } else {
          model.removeRow(selectedRecordIndex);
        }
      }
    });
    sl_panel.putConstraint(SpringLayout.NORTH, deleteKeyButton, 6, SpringLayout.SOUTH, scrollPane);
    deleteKeyButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    panel.add(deleteKeyButton);
  }
}
