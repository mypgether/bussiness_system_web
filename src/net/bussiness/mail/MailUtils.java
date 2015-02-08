package net.bussiness.mail;

public class MailUtils {
	/**
	 * @param toMailAddress
	 *            发送人邮箱
	 * @param mailTitle
	 *            邮件标题
	 * @param mailContent
	 *            邮件内容
	 */
	public static void sendMail(String toMailAddress, String mailTitle,
			String mailContent) {
		// 设置邮件服务器信息
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		// 邮箱用户名
		mailInfo.setUserName("913385255@qq.com");
		// 邮箱密码
		mailInfo.setPassword("1946729382myp");
		// 发件人邮箱
		mailInfo.setFromAddress("913385255@qq.com");
		// 收件人邮箱
		mailInfo.setToAddress(toMailAddress);
		// 邮件标题
		mailInfo.setSubject(mailTitle);
		// 邮件内容
		mailInfo.setContent(mailContent);

		// 发送html格式
		SimpleMailSender.sendTextMail(mailInfo);
		System.out.println("邮件发送完毕");
	}
}