//package util;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.*;
//import javax.mail.internet.*;
//import java.util.Date;
//import java.util.Enumeration;
//import java.util.Properties;
//import java.util.Vector;
//
//@Component
//public class MailUtils {
//	private static Logger logger = LoggerFactory.getLogger(MailUtils.class);
//
//	public void send(EmailMessage email) {
//		String host = email.getHost();
//		String user = email.getUser();
//		String pwd = email.getPwd();
//		String from = email.getFrom();
//		String to = email.getTo();
//		String cc = email.getCc();
//		String subject = email.getSubject();
//		String content = email.getContent();
//		Vector<String> files = email.getAffixFiles();
//		boolean isBodyHtml = email.isBodyHtml();
//
//		Properties props = new Properties();
//		// 设置发送邮件的邮件服务器的属性
//		props.put("mail.smtp.host", host);
//		// 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
//		props.put("mail.smtp.auth", "true");
//		// 用刚刚设置好的props对象构建一个session
//		Session session = Session.getDefaultInstance(props);
//		// 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
//		// 用（你可以在控制台（console)上看到发送邮件的过程）
//		//session.setDebug(true);
//		// 用session为参数定义消息对象
//		MimeMessage message = new MimeMessage(session);
//		try {
//			// 加载发件人地址
//			message.setFrom(new InternetAddress(from));
//			// 加载收件人地址
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//			// 设置抄送人
//			if (!StringUtils.isEmpty(cc)) {
//				message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(cc));
//			}
//			// 加载标题
//			message.setSubject(subject,"utf-8");
//			// 发送日期
//			message.setSentDate(new Date());
//
//			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
//			Multipart multipart = new MimeMultipart();
//			// 设置邮件的文本内容
//			BodyPart contentPart = new MimeBodyPart();
//			if(isBodyHtml){
//				contentPart.setContent(content, "text/html; charset=utf-8");
//			}else{
//				contentPart.setContent(content, "text/plain;charset=utf-8");
//			}
//			multipart.addBodyPart(contentPart);
//
//			if (null != files && !files.isEmpty()) {// 有附件
//				Enumeration<String> efile = files.elements();
//				while (efile.hasMoreElements()) {
//					String filename = efile.nextElement(); // 选择出每一个附件名
//					// 添加附件
//					BodyPart messageBodyPart = new MimeBodyPart();
//					DataSource source = new FileDataSource(filename);
//					// 添加附件的内容
//					messageBodyPart.setDataHandler(new DataHandler(source));
//					// 添加附件的标题
//					messageBodyPart.setFileName(MimeUtility.encodeText(source.getName()));
//					multipart.addBodyPart(messageBodyPart);
//				}
//				email.getAffixFiles().removeAllElements();
//			}
//
//			// 将multipart对象放到message中
//			message.setContent(multipart);
//			// 保存邮件
//			message.saveChanges();
//			// 发送邮件
//			Transport transport = session.getTransport("smtp");
//			// 连接服务器的邮箱
//			transport.connect(host, user, pwd);
//			// 把邮件发送出去
//			transport.sendMessage(message, message.getAllRecipients());
//			transport.close();
//
//			logger.info("email send tips: email from " + from + " to " + to);
//		} catch (Exception e) {
//			logger.error("email send error: " + e.getMessage());
//		}
//	}
//}
