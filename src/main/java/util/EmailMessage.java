package util;

import java.util.Vector;

public class EmailMessage {
	private String host = ""; // smtp服务器
	private String from = ""; // 发件人地址
	private String to = ""; // 收件人地址
	private String cc = "";	// 抄送人
	private String user = ""; // 用户名
	private String pwd = ""; // 密码
	private String subject = ""; // 邮件标题
	private String content = ""; // 邮件内容
	private Vector<String> affixFiles; // 附件地址
	private boolean isBodyHtml;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Vector<String> getAffixFiles() {
		return affixFiles;
	}

	public void setAffixFiles(Vector<String> affixFiles) {
		this.affixFiles = affixFiles;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isBodyHtml() {
		return isBodyHtml;
	}

	public void setBodyHtml(boolean isBodyHtml) {
		this.isBodyHtml = isBodyHtml;
	}

	@Override
	public String toString() {
		return "EmailMessage [host=" + host + ", from=" + from + ", to=" + to + ", user=" + user + ", pwd=" + pwd
				+ ", subject=" + subject + ", content=" + content + ", affixFiles=" + affixFiles + ", isBodyHtml="
				+ isBodyHtml + "]";
	}

}
