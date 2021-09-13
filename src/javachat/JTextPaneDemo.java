package javachat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
/*JTextPane을 사용하면 폰트, 굴기, 기울임, 정렬 같은
 * 다양한 서식을 사용할 수 있다.
 * [사용절차]
 * 1) SimpleAttributeSet 객체를 생성한다.
 * 2) StyleConstants 클래스의 static 메소드를 통해 1번 객체에 스타일 속성을 설정한다.
 * 3) StyleDocument의 setCharacterAttributes() 또는 setParagraphAttributes() 등의
 * 		메소드를 통해 원하는 영역에 스타일을 적용한다.
 * 		- setCharacterAttributes(): 문자 특성(폰트, 기울임, 강조, 밑줄, 글자색 등)
 * 		- setParagraphAttributes(): 문단 특성(문단 정렬, 들여쓰기, 문단 사이 간격 등)
 * */
public class JTextPaneDemo extends JFrame {

	JTextPane tp;
	JScrollPane sp;
	
	JPanel p = new JPanel(new BorderLayout());
	
	StyledDocument doc; // 텍스트 페인의 스타일 문서 모델

	public JTextPaneDemo() {
		super("::JTextPaneDemo::");
		Container cp = getContentPane();
		cp.add(p, "Center");
		p.setBackground(Color.white);
		tp = new JTextPane();
		sp = new JScrollPane(tp);
		p.add(sp, "Center");
		tp.setText("애플의 무선 충전패드 '에어 파워(Air Power)' 출시설이 또 제기됐다. "
				+ "오는 22일 개막하는 애플 세계 개발자 컨퍼런스(WWDC)에서 공개될 것이라는 전망이다.");
		// 1. 문서 모델 얻기
		doc = tp.getStyledDocument(); // 텍스트 페인의 문서 모델
		// 2. SimpleAttributeSet 객체 생성
		SimpleAttributeSet attr = new SimpleAttributeSet();
		// 3. attr 에 스타일 속성을 부여한다.
		StyleConstants.setFontFamily(attr, "궁서체");
		StyleConstants.setFontSize(attr, 28);
		// 서체 속성 부여
		
		// 4. 문서 모델에 해당 속성(attr)을 적용시킨다.
		doc.setCharacterAttributes(0, 20, attr, true);
		
		attr = new SimpleAttributeSet();
		StyleConstants.setUnderline(attr, true); // 밑줄
		StyleConstants.setItalic(attr, true); // 이탤릭체
		StyleConstants.setForeground(attr, Color.magenta); // 글자색
		StyleConstants.setBackground(attr, Color.yellow); // 배경색
		doc.setCharacterAttributes(20, 40, attr, true); //true는 덮어쓰기
		
		// 문단 특성 적용
		attr = new SimpleAttributeSet();
		StyleConstants.setAlignment(attr, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, tp.getText().length(), attr, true);
		
		// 문서 끝에 문자열 추가(커릿(커서) 이용)
		int caretPos = doc.getEndPosition().getOffset()-1; // 문서 끝의 커릿 위치를 알아내기
		// int로 반환 = 		Position.		Position -> int로	-1(0부터 시작이므로)		
		tp.setCaretPosition(caretPos); // 문서 끝에 커릿을 위치시킨다.
		attr = new SimpleAttributeSet();
		StyleConstants.setFontSize(attr, 30);
		StyleConstants.setForeground(attr, Color.blue);
		
		try {
			doc.insertString(caretPos, "\n이만 총총...\n", attr);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		// 텍스트 페인에 라벨 띄우기
		String str = "[Bangry님]\r\n";
		ImageIcon icon = new ImageIcon("blue.png");
		//ImageIcon icon = new ImageIcon(getClass().getResource("/talk.png"));
		
		JLabel lb = new JLabel(str, icon, JLabel.CENTER);
		lb.setPreferredSize(new Dimension(700,90)); // lb의 높이를 90, 폭을 700
		
		attr = new SimpleAttributeSet();
		StyleConstants.setAlignment(attr, StyleConstants.ALIGN_RIGHT);
		
		tp.setCaretPosition(doc.getEndPosition().getOffset()-1);
		tp.insertComponent(lb);
		doc.setParagraphAttributes(doc.getEndPosition().getOffset()-1,
				lb.getText().length(), attr, true); //문단 정렬을 통해 lb 정렬 적용
		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		JTextPaneDemo ae = new JTextPaneDemo();
		ae.setSize(500, 500);
		ae.setVisible(true);

	}

}