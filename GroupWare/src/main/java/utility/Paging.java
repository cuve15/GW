package utility;

public class Paging {
	//����¡ ���� ����	
	private int totalCount = 0 ; //�� ���ڵ� �Ǽ�
	private int totalPage = 0 ; //��ü ������ ��
	private int pageNumber = 0 ; //������ ������ �ѹ�(ǥ�� ������ �������� 1���� totalPage�����̴�.)
	private int pageSize = 0 ; //�� �������� ������ �Ǽ�
	private int beginRow = 0 ; //���� �������� ���� ��
	private int endRow = 0 ; //���� �������� �� ��
	private int pageCount = 3 ; //������ ������ ��ũ ��
	private int beginPage = 0 ; //����¡ ó�� ���� ������ ��ȣ
	private int endPage = 0 ; //����¡ ó�� �� ������ ��ȣ
	private int offset = 0 ;
	private int limit = 0 ;
	private String url = "" ; //http://localhost:8080/ex/list.tv
	private String pagingHtml = "";//�ϴ��� ���� ������ ��ũ
	
	//�˻��� ���� ���� �߰�
	private String whatColumn = "" ; //�˻� ���
	private String keyword = "" ; //�˻��� �ܾ� 

	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	public int getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public int getPageNumber() {
		return pageNumber;
	}


	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getBeginRow() {
		return beginRow;
	}


	public void setBeginRow(int beginRow) {
		this.beginRow = beginRow;
	}


	public int getEndRow() {
		return endRow;
	}


	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}


	public int getPageCount() {
		return pageCount;
	}


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public int getBeginPage() {
		return beginPage;
	}


	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}


	public int getOffset() {
		return offset;
	}


	public void setOffset(int offset) {
		this.offset = offset;
	}


	public int getLimit() {
		return limit;
	}


	public void setLimit(int limit) {
		this.limit = limit;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getPagingHtml() {
		return pagingHtml;
	}


	public void setPagingHtml(String pagingHtml) {
		this.pagingHtml = pagingHtml;
	}


	public String getWhatColumn() {
		return whatColumn;
	}


	public void setWhatColumn(String whatColumn) {
		this.whatColumn = whatColumn;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public Paging(
			String _pageNumber, //������ ������ ��ȣ
			String _pageSize,  //���������� ���̴� ������ ��
			int totalCount,
			String url, 
			String whatColumn, 
			String keyword) {		

		if( _pageNumber == null || _pageNumber.equals("null") || _pageNumber.equals("") ){
			_pageNumber = "1" ;
		}
		this.pageNumber = Integer.parseInt( _pageNumber ) ; 

		if( _pageSize == null || _pageSize.equals("null") || _pageSize.equals("") ){
			_pageSize = "3" ;
		}		
		this.pageSize = Integer.parseInt( _pageSize ) ;

		this.limit = pageSize ;

		this.totalCount = totalCount ; 

		this.totalPage = (int)Math.ceil((double)this.totalCount / this.pageSize) ;
							//�Ҽ����� �ø�ó��(ceil) �� �������� ���ϴ°�
		this.beginRow = ( this.pageNumber - 1 )  * this.pageSize  + 1 ;
		//�������� ��� ��� ���ڵ尡 ������ �ϴ� �۾�
		this.endRow =  this.pageNumber * this.pageSize ;
		//�������� ��� ��� ���ڵ尡 ������ �ϴ� �۾�

		if( this.endRow > this.totalCount ){
			this.endRow = this.totalCount  ;
		//endrow�� totalcount���� �� ũ�� ������ totalcount�� endrow�� ���� �ض�
		}

		this.beginPage = ( this.pageNumber - 1 ) / this.pageCount * this.pageCount + 1  ;
		//������ ������ �˷��ִ� ��ȣ  1~10���� ���̸� ���� ������ 11~20���� �����ִ°�
		this.endPage = this.beginPage + this.pageCount - 1 ;
		//������ ������ �˷��ִ� ��ȣ  1~10���� ���̸� ���� ������ 11~20���� �����ִ°�
		
		if( this.pageNumber > this.totalPage ){  
			this.pageNumber = this.totalPage ;
		}
		
		this.offset = ( pageNumber - 1 ) * pageSize ; 
		//�ǳʶ� ���ڵ� ����(���������� 2���� ���ڵ尡 �ִٰ� ġ��, 2�������� ������ 1�������� �ִ� 2���� �ǳʶٰ� ���ߵǴ°�)
		if( this.endPage > this.totalPage ){
			this.endPage = this.totalPage ;
		}

		this.url = url ;
		this.whatColumn = whatColumn ;
		this.keyword = keyword ;

		this.pagingHtml = getPagingHtml(url) ;
	
	}

	
	private String getPagingHtml( String url ){ 
		System.out.println("getPagingHtml url:"+url);
		String result = "" ;
		
		String added_param = "&whatColumn=" + whatColumn + "&keyword=" + keyword ; 
		
		//����
		if (this.beginPage != 1) { 
			result += "&nbsp;<a href='" + url  
					+ "?pageNumber=" + ( 1 ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>First Page</a>&nbsp;" ;
			result += "&nbsp;<a href='" + url 
					+ "?pageNumber=" + (this.beginPage - 1 ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>Before</a>&nbsp;" ;
		}
		
		//���
		for (int i = this.beginPage; i <= this.endPage ; i++) {
			if ( i == this.pageNumber ) {
				result += "&nbsp;<font color='red'>" + i + "</font>&nbsp;"	;
						
			} else {
				result += "&nbsp;<a href='" + url   
						+ "?pageNumber=" + i + "&pageSize=" + this.pageSize 
						+ added_param + "'>" + i + "</a>&nbsp;" ;
			}
		}
		
		//����
		if ( this.endPage != this.totalPage) {
			result += "&nbsp;<a href='" + url  
					+ "?pageNumber=" + (this.endPage + 1 ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>Next</a>&nbsp;" ;
			
			result += "&nbsp;<a href='" + url  
					+ "?pageNumber=" + (this.totalPage ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>Last</a>&nbsp;" ;
		}		
		
		return result ;
	}	
}
