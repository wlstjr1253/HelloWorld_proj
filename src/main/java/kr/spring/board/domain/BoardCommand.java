package kr.spring.board.domain;

import java.io.IOException;
import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class BoardCommand {
	private int num; // �۹�ȣ
	@NotEmpty
	private String title; // ������
	@NotEmpty
	private String content; // �۳���
	private int hit; // ��ȸ��
	private Date reg_date; // ��ϳ�¥
	private MultipartFile upload; // ���ε� ����
	// toString���� ����� byte Ÿ���� �����ʹ� ��û ��� ������
	// �ӵ��� ��������. toString �� ������ �ش�.
	private byte[] uploadfile; // DB�� ����� ����
	private String filename; // ���ϸ�
	private String ip; // ip�ּ�
	@NotEmpty
	private String id; // ȸ��id
	
	private int re_cnt; // ��� ��
	
	// MultipartFile -> byte[] ��ȯ
	// ���ϸ� ���ϱ�
	public void setUpload(MultipartFile upload) throws IOException {
		this.upload = upload;
					 // byte[] ������ ����
		setUploadfile(upload.getBytes());
                     // ���ϸ�
		setFilename(upload.getOriginalFilename());
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public MultipartFile getUpload() {
		return upload;
	}

	public byte[] getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(byte[] uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRe_cnt() {
		return re_cnt;
	}

	public void setRe_cnt(int re_cnt) {
		this.re_cnt = re_cnt;
	}

	// (����) byte[]�� uploadfile�� ����!!!!
	@Override
	public String toString() {
		return "BoardCommand [num=" + num + ", title=" + title + ", content=" + content + ", hit=" + hit + ", reg_date="
				+ reg_date + ", upload=" + upload + ", filename=" + filename + ", ip=" + ip + ", id=" + id + ", re_cnt="
				+ re_cnt + "]";
	}
	
}
