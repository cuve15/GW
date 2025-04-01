package admin.model;

import org.hibernate.validator.constraints.NotEmpty;

public class CmmCodeBean {
		
		@NotEmpty(message="공통코드 Class를 입력하세요.")
		private String cmm_class;
		@NotEmpty(message="공통코드 코드를 입력하세요.")
		private String cmm_cd;
		@NotEmpty(message="공통코드 이름을 입력하세요.")
		private String cmm_nm;
		
		
		public String getCmm_class() {
			return cmm_class;
		}
		public void setCmm_class(String cmm_class) {
			this.cmm_class = cmm_class;
		}
		public String getCmm_cd() {
			return cmm_cd;
		}
		public void setCmm_cd(String cmm_cd) {
			this.cmm_cd = cmm_cd;
		}
		public String getCmm_nm() {
			return cmm_nm;
		}
		public void setCmm_nm(String cmm_nm) {
			this.cmm_nm = cmm_nm;
		}
}
