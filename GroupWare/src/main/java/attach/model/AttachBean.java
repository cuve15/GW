package attach.model;
import java.sql.Timestamp;

public class AttachBean {
    private String attach_id;
    private String con_key1;
    private String con_key2;
    private String con_key3;
    private String org_file_name;
    private String server_file_name;
    private long file_size;
    private Timestamp insert_dtm;
    
	public String getAttach_id() {
		return attach_id;
	}
	public String getCon_key1() {
		return con_key1;
	}
	public String getCon_key2() {
		return con_key2;
	}
	public String getCon_key3() {
		return con_key3;
	}
	public String getOrg_file_name() {
		return org_file_name;
	}
	public String getServer_file_name() {
		return server_file_name;
	}
	public long getFile_size() {
		return file_size;
	}
	public Timestamp getInsert_dtm() {
		return insert_dtm;
	}
	public void setAttach_id(String attach_id) {
		this.attach_id = attach_id;
	}
	public void setCon_key1(String con_key1) {
		this.con_key1 = con_key1;
	}
	public void setCon_key2(String con_key2) {
		this.con_key2 = con_key2;
	}
	public void setCon_key3(String con_key3) {
		this.con_key3 = con_key3;
	}
	public void setOrg_file_name(String org_file_name) {
		this.org_file_name = org_file_name;
	}
	public void setServer_file_name(String server_file_name) {
		this.server_file_name = server_file_name;
	}
	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}
	public void setInsert_dtm(Timestamp insert_dtm) {
		this.insert_dtm = insert_dtm;
	}

	
}


