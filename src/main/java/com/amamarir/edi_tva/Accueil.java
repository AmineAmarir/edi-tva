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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.amamarir.edi_tva.data.DeclarationReleveDeduction;

/**
 * @author amamarir
 *
 */
public class Accueil extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
	private JTextField idFiscal = new JTextField();
	private JPanel idPan = new JPanel();
	private JLabel label = new JLabel("Veuillez entrer l'identifiant fiscal");
	private JPanel lab = new JPanel();
	private JPanel bouton = new JPanel();
	private JPanel pan = new JPanel();
	private JTextField annee = new JTextField("2017");
	private JComboBox<String> periode = new JComboBox<>();
	private JTextField regime = new JTextField("2");
	private JButton fermer = new JButton("Fermer");
	private JButton suivant = new JButton("Suivant");
	private JTextField nom = new JTextField();
	private JTextField prenom = new JTextField();

	public Accueil() {
		this.setTitle("Accueil - EDI TVA");
		this.setSize(300, 275);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		container.setLayout(new BorderLayout());
		init();
		this.setContentPane(container);
		this.setVisible(true);
	}

//	private void init(){
//		final JLabel nomlab = new JLabel("Nom :");
//		final JLabel prenomlab = new JLabel("Prénom :");
//		nomlab.setHorizontalAlignment(JLabel.CENTER);
//		prenomlab.setHorizontalAlignment(JLabel.CENTER);
//		nom.setHorizontalAlignment(JTextField.CENTER);
//		prenom.setHorizontalAlignment(JTextField.CENTER);
//		final JPanel nomPan = new JPanel();
//		final JPanel prenomPan = new JPanel();
//		nomPan.add(nomlab);
//		nom.setPreferredSize(new Dimension(150, 25));
//		nomPan.add(nom);
//		prenomPan.add(prenomlab);
//		prenom.setPreferredSize(new Dimension(150, 25));
//		prenomPan.add(prenom);
//		label.setHorizontalAlignment(JLabel.CENTER);
//		lab.add(label);
//		bouton.add(suivant);
//		bouton.add(fermer);
//		idFiscal.setPreferredSize(new Dimension(250, 25));
//		idPan.add(idFiscal);
//		final JLabel anneelab = new JLabel("Année :");
//		anneePan.add(anneelab);
//		anneePan.add(annee);
//		annee.setHorizontalAlignment(JTextField.CENTER);
//		idFiscal.setHorizontalAlignment(JTextField.CENTER);
//		regime.setHorizontalAlignment(JTextField.CENTER);
//		((JLabel)periode.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
//		annee.setPreferredSize(new Dimension(150, 25));
//		final JLabel periodelab = new JLabel("Période :");
//		periodePan.add(periodelab);
//		periodePan.add(periode);
//		periode.setPreferredSize(new Dimension(150, 25));
//		periode.addItem("1");
//		periode.addItem("2");
//		periode.addItem("3");
//		periode.addItem("4");
//		final JLabel regimelab = new JLabel("Régime :");
//		regimePan.add(regimelab);
//		regimePan.add(regime);
//		regime.setPreferredSize(new Dimension(150, 25));
//		pan.setLayout(new GridLayout(8, 1));
//		pan.add(lab);
//		pan.add(idPan);
//		pan.add(nomPan);
//		pan.add(prenomPan);
//		pan.add(anneePan);
//		pan.add(periodePan);
//		pan.add(regimePan);
//		pan.add(bouton);
//		fermer.addActionListener((e) -> dispose());
//		suivant.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				
//				if (idFiscal.getText() != null && !idFiscal.getText().trim().equals("")) {
//					new TvaTable(Accueil.this.chargeData()).addWindowListener(new WindowAdapter() {
//
//						public void windowClosed(WindowEvent e) {
//							visible();
//						}
//					});
//					invisible();
//				} else {
//		                idFiscal.setBorder(BorderFactory.createLineBorder(Color.red));
//				}
//			}
//		});
//		container.add(pan, BorderLayout.CENTER);
//	}
	
	private void init(){
		final JLabel nomlab = new JLabel("Nom :");
		final JLabel prenomlab = new JLabel("Prénom :");
		final JLabel anneelab = new JLabel("Année :");
		final JLabel periodelab = new JLabel("Période :");
		final JLabel regimelab = new JLabel("Régime :");
		
		final JPanel bothPan = new JPanel();
		final JPanel leftPan = new JPanel();
		final JPanel rightPan = new JPanel();
		
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setPreferredSize(new Dimension(250, 25));
		lab.add(label);
		lab.setPreferredSize(new Dimension(250, 25));
		idFiscal.setHorizontalAlignment(JTextField.CENTER);
		idFiscal.setPreferredSize(new Dimension(250, 25));
		idPan.setPreferredSize(new Dimension(0, 60));
		idPan.setLayout(new GridLayout(2, 1));
		idPan.add(lab);
		final JPanel idFiscPan = new JPanel();
		idFiscPan.add(idFiscal);
		idPan.add(idFiscPan);

		nomlab.setHorizontalAlignment(JLabel.RIGHT);
		prenomlab.setHorizontalAlignment(JLabel.RIGHT);
		anneelab.setHorizontalAlignment(JLabel.RIGHT);
		periodelab.setHorizontalAlignment(JLabel.RIGHT);
		regimelab.setHorizontalAlignment(JLabel.RIGHT);
		
		leftPan.setLayout(new GridLayout(5,1,10,10));
		leftPan.add(nomlab);
		leftPan.add(prenomlab);
		leftPan.add(anneelab);
		leftPan.add(periodelab);
		leftPan.add(regimelab);
		leftPan.setPreferredSize(new Dimension(75, 0));
		
		nom.setPreferredSize(new Dimension(150, 25));
		prenom.setPreferredSize(new Dimension(150, 25));
		annee.setPreferredSize(new Dimension(150, 25));
		periode.setPreferredSize(new Dimension(150, 25));
		regime.setPreferredSize(new Dimension(150, 25));
		
		nom.setHorizontalAlignment(JTextField.CENTER);
		prenom.setHorizontalAlignment(JTextField.CENTER);
		annee.setHorizontalAlignment(JTextField.CENTER);
		regime.setHorizontalAlignment(JTextField.CENTER);
		((JLabel)periode.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		rightPan.setLayout(new GridLayout(5,1));
		final JPanel nomPan = new JPanel();
		nomPan.add(nom);
		rightPan.add(nomPan);
		final JPanel prenomPan = new JPanel();
		prenomPan.add(prenom);
		rightPan.add(prenomPan);
		final JPanel anneePan = new JPanel();
		anneePan.add(annee);
		rightPan.add(anneePan);
		final JPanel periodePan = new JPanel();
		periode.addItem("1");
		periode.addItem("2");
		periode.addItem("3");
		periode.addItem("4");
		periodePan.add(periode);
		rightPan.add(periodePan);
		final JPanel regimePan = new JPanel();
		regimePan.add(regime);
		rightPan.add(regimePan);
		
		bothPan.setLayout(new BorderLayout());
		bothPan.add(leftPan, BorderLayout.WEST);
		bothPan.add(rightPan, BorderLayout.CENTER);

		bouton.add(suivant);
		bouton.add(fermer);
		
		fermer.addActionListener((e) -> dispose());
		suivant.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean go = true;
				
				if (idFiscal.getText() != null && !idFiscal.getText().trim().equals("")) {
					idFiscal.setBorder(BorderFactory.createLineBorder(Color.GRAY));
					go = go && true;
				} else {
		                idFiscal.setBorder(BorderFactory.createLineBorder(Color.red));
		                go = go && false;
				}
				
				if (nom.getText() != null && !nom.getText().trim().equals("")) {
					nom.setBorder(BorderFactory.createLineBorder(Color.GRAY));
					go = go && true;
				} else {
					nom.setBorder(BorderFactory.createLineBorder(Color.red));
					go = go && false;
				}
				
				if (prenom.getText() != null && !prenom.getText().trim().equals("")) {
					prenom.setBorder(BorderFactory.createLineBorder(Color.GRAY));
					go = go && true;
				} else {
					prenom.setBorder(BorderFactory.createLineBorder(Color.red));
					go = go && false;
				}
				
				if (annee.getText() != null && !annee.getText().trim().equals("")) {
					annee.setBorder(BorderFactory.createLineBorder(Color.GRAY));
					go = go && true;
				} else {
					annee.setBorder(BorderFactory.createLineBorder(Color.red));
					go = go && false;
				}
				
				if (regime.getText() != null && !regime.getText().trim().equals("")) {
					regime.setBorder(BorderFactory.createLineBorder(Color.GRAY));
					go = go && true;
				} else {
					regime.setBorder(BorderFactory.createLineBorder(Color.red));
					go = go && false;
				}
				
				if(go){
					new TvaTable(Accueil.this.chargeData(), prenom.getText().trim().toUpperCase() + " " + nom.getText().trim().toUpperCase()).addWindowListener(new WindowAdapter() {

						public void windowClosed(WindowEvent e) {
							visible();
						}
					});
					invisible();
				}
			}
		});
		
		pan.setLayout(new BorderLayout());
		pan.add(idPan, BorderLayout.NORTH);
		pan.add(bothPan, BorderLayout.CENTER);
		pan.add(bouton, BorderLayout.SOUTH);
		container.add(pan, BorderLayout.CENTER);
	}
	
	public void invisible() {
		this.setVisible(false);
	}

	public void visible() {
		this.setVisible(true);
	}
	
	private DeclarationReleveDeduction chargeData(){
		DeclarationReleveDeduction drd = new DeclarationReleveDeduction();
		drd.identifiantFiscal = idFiscal.getText();
		drd.annee = annee.getText();
		drd.periode = periode.getSelectedItem().toString();
		drd.regime = regime.getText();
		return drd;
	}
}