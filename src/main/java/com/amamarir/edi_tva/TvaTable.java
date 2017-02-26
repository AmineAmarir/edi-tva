/*******************************************************************************
 * MIT License
 *
 * Copyright (c) 2017 Amine Amarir
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 ******************************************************************************/
package com.amamarir.edi_tva;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.amamarir.edi_tva.data.DeclarationReleveDeduction;
import com.amamarir.edi_tva.data.mp;
import com.amamarir.edi_tva.data.rd;
import com.amamarir.edi_tva.data.refF;

/**
 * @author amamarir
 *
 */
public class TvaTable extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8701017221918246265L;
	private JPanel container = new JPanel();
	private JLabel label1 = new JLabel();
	private JLabel label2 = new JLabel();
	private JPanel header = new JPanel();
	private JPanel footer = new JPanel();
	private JFileChooser chooser;
	private String choosertitle = "Choisir Dossier";
	private JTable tab;
	private boolean print = false;

	private DeclarationReleveDeduction declarationReleveDeduction;

	public TvaTable(DeclarationReleveDeduction declarationReleveDeduction, String name) {
		this.declarationReleveDeduction = declarationReleveDeduction;
		this.setTitle("Saisi d'éléments - EDI TVA");
		this.setSize(1000, 700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		container.setLayout(new BorderLayout());

		Font police = new Font("Tahona", Font.ITALIC, 22);
		label1.setText("Veuillez entrer vos éléments");
		label1.setFont(police);
		header.add(label1);
		header.setBorder(BorderFactory.createLineBorder(Color.black));
		container.add(header, BorderLayout.NORTH);

		String[] titre = { "Num. Fac", "Desc", "Montant HT", "Taux", "TVA", "TTC", "Id. Fisc.", "Nom", "ICE",
				"Date Pai.", "Date Fac." };
		Object[][] data = new Object[50][11];
		tab = new JTable(data, titre);
		tab.getTableHeader().setForeground(Color.white);
		tab.getTableHeader().setBackground(Color.black);
		tab.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.black));
		tab.setBorder(BorderFactory.createLineBorder(Color.black));
		JScrollPane pan = new JScrollPane(tab);
		pan.setBorder(BorderFactory.createLineBorder(Color.black));
		pan.setPreferredSize(new Dimension(650, 575));
		container.add(pan, BorderLayout.CENTER);

		label2.setText("Id. Fiscal : " + this.declarationReleveDeduction.identifiantFiscal + " - Année : "
				+ declarationReleveDeduction.annee + " - Période : " + declarationReleveDeduction.periode
				+ " - Régime : " + declarationReleveDeduction.regime);
		label2.setFont(police);
		final JPanel footerLab = new JPanel();
		final JPanel footerButton = new JPanel();
		footerLab.add(label2);
		final JButton exporter = new JButton("Exporter");
		final JButton retour = new JButton("Retour");
		String filename = "TVA_" + name + "_" + this.declarationReleveDeduction.identifiantFiscal + "_"
				+ this.declarationReleveDeduction.annee + "_" + this.declarationReleveDeduction.periode + ".xml";
		retour.addActionListener((e) -> this.dispose());
		exporter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean error = checkData();
				if (error) {
					JOptionPane.showMessageDialog(TvaTable.this, "Une erreur est existante, Veuillez la corriger.");
				} else {
					chooser = new JFileChooser();
					chooser.setCurrentDirectory(new java.io.File("."));
					chooser.setDialogTitle(choosertitle);
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					//
					// disable the "All files" option.
					//
					chooser.setAcceptAllFileFilterUsed(false);
					//
					if (chooser.showOpenDialog(container) == JFileChooser.APPROVE_OPTION) {
						chargeDataToExport();
						if (print)
							exportToXML(TvaTable.this.declarationReleveDeduction,
									chooser.getSelectedFile() + File.separator + filename);
					}
				}
			}
		});
		footerButton.add(retour);
		footerButton.add(exporter);
		footer.setLayout(new GridLayout(2, 1));
		footer.add(footerLab);
		footer.add(footerButton);
		footer.setBorder(BorderFactory.createLineBorder(Color.black));
		container.add(footer, BorderLayout.SOUTH);

		this.setContentPane(container);
		this.setVisible(true);
	}

	private boolean checkData() {
		TableModel model = tab.getModel();
		int rowLength = model.getRowCount();

		boolean err = false;
		for (int i = 0; i < rowLength; i++) {

			boolean[] utilise = new boolean[11];
			boolean[] error = new boolean[11];

			Object numFac = model.getValueAt(i, 0);
			if (numFac == null) {
				utilise[0] = false;
				error[0] = false;
			} else {
				String str = numFac.toString().trim();
				if (str.equals("")) {
					utilise[0] = false;
					error[0] = true;
				} else {
					utilise[0] = true;
					error[0] = false;
				}
			}

			Object desc = model.getValueAt(i, 1);
			if (desc == null) {
				utilise[1] = false;
				error[1] = false;
			} else {
				String str = desc.toString().trim();
				if (str.equals("")) {
					utilise[1] = false;
					error[1] = true;
				} else {
					utilise[1] = true;
					error[1] = false;
				}
			}

			Object montantHT = model.getValueAt(i, 2);
			if (montantHT == null) {
				utilise[2] = false;
				error[2] = false;
			} else {
				String str = montantHT.toString().trim();
				if (str.equals("")) {
					utilise[2] = false;
					error[2] = true;
				} else {
					utilise[2] = true;
					error[2] = false;
				}
			}

			Object taux = model.getValueAt(i, 3);
			if (taux == null) {
				utilise[3] = false;
				error[3] = false;
			} else {
				String str = taux.toString().trim();
				if (str.equals("")) {
					utilise[3] = false;
					error[3] = true;
				} else {
					utilise[3] = true;
					error[3] = false;
				}
			}

			Object tVA = model.getValueAt(i, 4);
			if (tVA == null) {
				utilise[4] = false;
				error[4] = false;
			} else {
				String str = tVA.toString().trim();
				if (str.equals("")) {
					utilise[4] = false;
					error[4] = true;
				} else {
					utilise[4] = true;
					error[4] = false;
				}
			}

			Object TTC = model.getValueAt(i, 5);
			if (TTC == null) {
				utilise[5] = false;
				error[5] = false;
			} else {
				String str = TTC.toString().trim();
				if (str.equals("")) {
					utilise[5] = false;
					error[5] = true;
				} else {
					utilise[5] = true;
					error[5] = false;
				}
			}

			Object idFisc = model.getValueAt(i, 6);
			if (idFisc == null) {
				utilise[6] = false;
				error[6] = false;
			} else {
				String str = idFisc.toString().trim();
				if (str.equals("")) {
					utilise[6] = false;
					error[6] = true;
				} else {
					utilise[6] = true;
					error[6] = false;
				}
			}

			Object Nom = model.getValueAt(i, 7);
			if (Nom == null) {
				utilise[7] = false;
				error[7] = false;
			} else {
				String str = Nom.toString().trim();
				if (str.equals("")) {
					utilise[7] = false;
					error[7] = true;
				} else {
					utilise[7] = true;
					error[7] = false;
				}
			}

			Object ice = model.getValueAt(i, 8);
			if (ice == null) {
				utilise[8] = false;
				error[8] = false;
			} else {
				String str = ice.toString().trim();
				if (str.equals("")) {
					utilise[8] = false;
					error[8] = true;
				} else {
					utilise[8] = true;
					error[8] = false;
				}
			}

			Object datePai = model.getValueAt(i, 9);
			if (datePai == null) {
				utilise[9] = false;
				error[9] = false;
			} else {
				String str = datePai.toString().trim();
				if (str.equals("")) {
					utilise[9] = false;
					error[9] = true;
				} else {
					utilise[9] = true;
					error[9] = false;
				}
			}

			Object dateFac = model.getValueAt(i, 10);
			if (dateFac == null) {
				utilise[10] = false;
				error[10] = false;
			} else {
				String str = dateFac.toString().trim();
				if (str.equals("")) {
					utilise[10] = false;
					error[10] = true;
				} else {
					utilise[10] = true;
					error[10] = false;
				}
			}
			
			if(i == 0){
				boolean test = true;
				for(int j = 0 ; j < utilise.length; j++){
					test = test && !utilise[j];
				}
				if(test){
					err = true;
					break;
				}
			}
			
			boolean used = false, notused = false;
			for (int j = 0; j < error.length; j++) {
				if (error[j]) {
					if (utilise[j]) {
						err = true;
						break;
					} else {
						notused = true;
						if(used){
							err = true;
							break;
						}
					}
					
				} else {
					if (utilise[j]) {
						used = true;
						if(notused){
							err = true;
							break;
						}
					} else {
						notused = true;
						if(used){
							err = true;
							break;
						}
					}
				}
			}
			
			if(err){
				break;
			}

		}
		return err;
	}

	private void chargeDataToExport() {
		TableModel model = tab.getModel();
		int rowLength = model.getRowCount();
		System.out.println(tab.getModel().getValueAt(0, 0));
		for (int i = 0; i < rowLength; i++) {
			System.out.println(model);
			System.out.println(model.getValueAt(0, 0));
			String numFac = model.getValueAt(i, 0).toString();

			if (numFac == null || numFac.trim().equals("")) {
				if (i == 0)
					print = false;
				else
					print = true;
				break;
			}

			String desc = model.getValueAt(i, 1).toString();
			String montantHT = model.getValueAt(i, 2).toString();
			String taux = model.getValueAt(i, 3).toString();
			String tVA = model.getValueAt(i, 4).toString();
			String TTC = model.getValueAt(i, 5).toString();
			String idFisc = model.getValueAt(i, 6).toString();
			String Nom = model.getValueAt(i, 7).toString();
			String ice = model.getValueAt(i, 8).toString();
			String datePai = model.getValueAt(i, 9).toString();
			String dateFac = model.getValueAt(i, 10).toString();

			rd rr = new rd();
			rr.ord = "" + (i + 1);
			rr.num = numFac.trim();
			rr.des = desc.trim();
			rr.mht = montantHT.trim();
			rr.tva = tVA.trim();
			rr.ttc = TTC.trim();
			rr.refF = new refF();
			rr.refF.idf = idFisc.trim();
			rr.refF.nom = Nom.trim();
			rr.refF.ice = ice.trim();
			rr.tx = taux.trim();
			rr.mp = new mp();
			rr.dfac = dateFac.trim();
			rr.dpai = datePai.trim();
			declarationReleveDeduction.releveDeductions.rds.add(rr);
		}
	}

	private void exportToXML(DeclarationReleveDeduction drd, String filename) {

		try {
			JAXBContext context = JAXBContext.newInstance(DeclarationReleveDeduction.class);
			Marshaller m = context.createMarshaller();
			// for pretty-print XML in JAXB
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Write to System.out for debugging
			m.marshal(drd, System.out);

			// Write to File
			m.marshal(drd, new File(filename));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
