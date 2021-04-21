import javax.swing.JFrame;

public class Test
{
	public static void main(String[] args)
	{
		//FrameNullLayout testFrame = new FrameNullLayout();
		//FrameFlowLayout testFrame = new FrameFlowLayout();
		FrameBoxLayout testFrame = new FrameBoxLayout();

		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testFrame.setSize(500, 700);
		testFrame.setVisible(true);
	}
}