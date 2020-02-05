package learn;

public class RegisterUser {


	/*{
		"clientUserIdStr": "100001",
		"inviteCode": "04e19a5e80694490afaf8c12f7f968c7",
		"status": "Registered",
		"inviteUrl": "https://buy.itunes.apple.com/WebObjects/MZFinance.woa/wa/associateVPPUserWithITSAccount?cc=us&inviteCode=04e19a5e80694490afaf8c12f7f968c7&mt=8",
		"userId": 87558792
	}
	*/
	
    private String clientUserIdStr;
    private String inviteCode;
    private String status;
    private String inviteUrl;
    private Integer userId;


	public String getClientUserIdStr() {
		return clientUserIdStr;
	}
	public void setClientUserIdStr(String clientUserIdStr) {
		this.clientUserIdStr = clientUserIdStr;
	}
	public String getInviteCode() {
		return inviteCode;
	}
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInviteUrl() {
		return inviteUrl;
	}
	public void setInviteUrl(String inviteUrl) {
		this.inviteUrl = inviteUrl;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
    }

    public RegisterUser() {
    }

    @Override
    public String toString() {
        return "RegisterUser [clientUserIdStr=" + clientUserIdStr + ", inviteCode=" + inviteCode + ", inviteUrl="
                + inviteUrl + ", status=" + status + ", userId=" + userId + "]";
    }
}
    
