package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuSolverGUI extends JFrame {

	private JTextField[][] sudokuCells; // Mảng các ô nhập giá trị Sudoku
	private JButton solveButton; // Nút để kích thích quá trình giải Sudoku
	private JButton clearButton;

	public SudokuSolverGUI() {

		sudokuCells = new JTextField[9][9];
		solveButton = new JButton("Solve");
		clearButton = new JButton("Clear");

		JPanel sudokuPanel = new JPanel(new GridLayout(9, 9, 2, 2));
		for (int i = 0; i < 9; i++) {

			for (int j = 0; j < 9; j++) {
				sudokuCells[i][j] = new JTextField(1);
				sudokuPanel.add(sudokuCells[i][j]);
			}
		}

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(solveButton);
		buttonPanel.add(clearButton);

		solveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[][] sudokuArray = new int[9][9];

				// Lấy giá trị từ ô nhập và tạo mảng Sudoku
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						try {
							sudokuArray[i][j] = Integer.parseInt(sudokuCells[i][j].getText());
						} catch (NumberFormatException ex) {
							// Xử lý nếu không phải là số nguyên
							sudokuArray[i][j] = 0; // Đặt giá trị là 0 nếu không phải là số nguyên hợp lệ
						}
					}
				}
				clearButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Xóa tất cả các số nhập vào
						for (int i = 0; i < 9; i++) {
							for (int j = 0; j < 9; j++) {
								sudokuCells[i][j].setText("");
							}
						}
					}
				});

				// Tạo một cá thể với Sudoku nhập vào
				Individual initialIndividual = new Individual(sudokuArray);

				// Giải Sudoku bằng thuật toán di truyền
				Individual solution = initialIndividual.sudokuSolve();

				if (solution != null) {
					// Hiển thị giải pháp trên giao diện người dùng
					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							sudokuCells[i][j].setText(Integer.toString(solution.getGenes().get(i).getGene().get(j)));
						}
					}
				} else {
					JOptionPane.showMessageDialog(SudokuSolverGUI.this, "Không tìm thấy giải pháp.", "Sudoku Solver",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		setLayout(new BorderLayout());
		add(sudokuPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		setTitle("Sudoku Solver");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 250);
		setResizable(true);
		setSize(400, 400);
		setVisible(true);
	}

	public static void main(String[] args) {
		new SudokuSolverGUI();
	}
}
