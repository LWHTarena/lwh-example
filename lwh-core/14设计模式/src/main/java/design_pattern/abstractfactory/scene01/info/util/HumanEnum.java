package design_pattern.abstractfactory.scene01.info.util;
/**
 * @author liwenhao
 * ����������Щ���͵��ˣ��г���
 * JDK1.5��ʼ����enum���ͳ���Ҳ��Ŀ�ĵģ�ϲӭc����Աת����
 */
public enum HumanEnum {
	//�����������������Ͷ��������
	YellowMaleHuman("com.tarena.infa.abs.YellowMaleHuman"),
	YellowFeMaleHuman("com.tarena.infa.abs.YellowFemaleHuman"),
	WhiteMaleHuman("com.tarena.infa.abs.whiteMaleHuman"),
	whiteFemaleHuman("com.tarena.infa.abs.whiteFemaleHuman"),
	BlackMaleHuman("com.tarena.infa.abs.BlackMaleHuman("),
	BlackFemaleHuman("com.tarena.infa.abs.BlackFemaleHuman(");
	
	private String value ="";
	//���幹�캯����Ŀ����Date��value�����͵�����
	private HumanEnum(String value){
		this.value =value;
	}
	
	public String getValue(){
		return this.value;
	}
	/*
	 * java enum���;�����ʹ�ã�������Ҫʹ�ö�̬���̳еȷ���
	 * �Ͼ���class��ȫ���Դ���enum
	 */
}
