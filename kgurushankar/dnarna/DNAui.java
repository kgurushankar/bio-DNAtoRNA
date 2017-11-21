package kgurushankar.dnarna;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DNAui extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton buttonDNA, buttonRNA, buttonLetters, buttonReset;
	private JFormattedTextField fieldDNA, fieldRNA, fieldLetters;

	public DNAui() {

		buttonDNA = new JButton("Convert");
		buttonDNA.setPreferredSize(new Dimension(80, 30));
		buttonDNA.setToolTipText("Convert from DNA");
		buttonDNA.addActionListener(this);

		fieldDNA = new JFormattedTextField("DNA");
		fieldDNA.setPreferredSize(new Dimension(120, 30));
		fieldDNA.setToolTipText("DNA");
		fieldDNA.setColumns(40);
		fieldDNA.setEditable(true);

		buttonRNA = new JButton("Convert");
		buttonRNA.setPreferredSize(new Dimension(80, 30));
		buttonRNA.setToolTipText("Convert from RNA");
		buttonRNA.addActionListener(this);

		fieldRNA = new JFormattedTextField("RNA");
		fieldRNA.setPreferredSize(new Dimension(120, 30));
		fieldDNA.setToolTipText("RNA");
		fieldRNA.setColumns(40);
		fieldRNA.setEditable(true);

		buttonLetters = new JButton("Convert");
		buttonLetters.setPreferredSize(new Dimension(80, 30));
		buttonLetters.setToolTipText("Convert from Letters");
		buttonLetters.addActionListener(this);

		fieldLetters = new JFormattedTextField("Amino Acids in letter format");
		fieldLetters.setPreferredSize(new Dimension(120, 30));
		fieldDNA.setToolTipText("Amino Acids in letter format");
		fieldLetters.setColumns(40);
		fieldLetters.setEditable(true);

		buttonReset = new JButton("Reset");
		buttonReset.setPreferredSize(new Dimension(80, 30));
		buttonReset.setToolTipText("Reset all fields");
		buttonReset.addActionListener(this);

		JPanel fieldPane = new JPanel(new GridLayout(0, 1));
		fieldPane.add(fieldDNA);
		fieldPane.add(fieldRNA);
		fieldPane.add(fieldLetters);

		JPanel buttonPane = new JPanel(new GridLayout(0, 1));
		buttonPane.add(buttonDNA);
		buttonPane.add(buttonRNA);
		buttonPane.add(buttonLetters);

		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		add(fieldPane, BorderLayout.CENTER);
		add(buttonPane, BorderLayout.LINE_END);
		add(buttonReset, BorderLayout.PAGE_END);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			JButton button = (JButton) e.getSource();
			String DNA, RNA, Letters;
			DNAwork op = new DNAwork();
			if (button == buttonDNA) {
				DNA = fieldDNA.getText();
				RNA = op.DNAtoRNA(DNA);
				Letters = op.RNAtoLetters(RNA);

				fieldDNA.setText(DNA);
				fieldRNA.setText(RNA);
				fieldLetters.setText(Letters);
			} else if (button == buttonRNA) {
				RNA = fieldRNA.getText();
				DNA = op.RNAtoDNA(RNA);
				Letters = op.RNAtoLetters(RNA);

				fieldDNA.setText(DNA);
				fieldRNA.setText(RNA);
				fieldLetters.setText(Letters);
			} else if (button == buttonLetters) {
				Letters = fieldLetters.getText();
				RNA = op.LetterstoRNA(Letters);
				DNA = op.RNAtoDNA(RNA);

				fieldDNA.setText(DNA);
				fieldRNA.setText(RNA);
				fieldLetters.setText(Letters);
			} else if (button == buttonReset) {
				DNA = "";
				RNA = "";
				Letters = "";
				fieldDNA.setText(DNA);
				fieldRNA.setText(RNA);
				fieldLetters.setText(Letters);
			}
			repaint();
		} catch (Exception ex) {

		}
	}

	public static void main(String[] args) {
		JFrame window = new JFrame("DNA to RNA");
		window.setBounds(300, 300, 600, 200);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		DNAui panel = new DNAui();
		panel.setBackground(Color.WHITE); // The default color is a light gray
		Container c = window.getContentPane();
		c.add(panel);

		window.setResizable(false);
		window.setVisible(true);
	}

}
