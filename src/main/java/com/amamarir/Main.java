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
package com.amamarir;

import com.amamarir.edi_tva.Accueil;

/**
 * @author amamarir
 *
 */
public class Main {

	public static void main(String[] args) {
//		test();
		new Accueil();
//		new TvaTable();
//		JFrame jf = new JFrame();
//		jf.setContentPane(new DemoJFileChooser());
//		jf.setVisible(true);
//		jf.setSize(300, 250);
//		jf.setResizable(false);
//		jf.setLocationRelativeTo(null);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
//	private static void test(){
//		DeclarationReleveDeduction drd = new DeclarationReleveDeduction();
//		rd rr = new rd();
//		drd.releveDeductions.rds.add(rr);
//		rr.mp = new mp();
//		rr.refF = new refF();
//		jaxbObjectToXML(drd);
//	}
//	
//	private static void jaxbObjectToXML(DeclarationReleveDeduction emp) {
//
//        try {
//            JAXBContext context = JAXBContext.newInstance(DeclarationReleveDeduction.class);
//            Marshaller m = context.createMarshaller();
//            //for pretty-print XML in JAXB
//            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//
//            // Write to System.out for debugging
//             m.marshal(emp, System.out);
//
//            // Write to File
//            m.marshal(emp, new File("C:\\Users\\amari\\Desktop\\test.xml"));
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//    }
	
}
