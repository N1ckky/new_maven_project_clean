package Nick_Maven.Framework.model;

public class ParamsCloudGoogleCalculator {

    private String vmClass;
    private String instance;
    private String region;
    private String localSsd;
    private String commitmentTerm;
    private String totalPrice;

    public ParamsCloudGoogleCalculator(String vmClass, String instance, String region, String localSsd,
                                       String commitmentTerm, String totalPrice) {
        this.vmClass = vmClass;
        this.instance = instance;
        this.region = region;
        this.localSsd = localSsd;
        this.commitmentTerm = commitmentTerm;
        this.totalPrice = totalPrice;
    }

    public String getVmClass() {
        return vmClass;
    }

    public void setVmClass(String vmClass) {
        this.vmClass = vmClass;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLocalSsd() {
        return localSsd;
    }

    public void setLocalSsd(String localSsd) {
        this.localSsd = localSsd;
    }

    public String getCommitmentTerm() {
        return commitmentTerm;
    }

    public void setCommitmentTerm(String commitmentTerm) {
        this.commitmentTerm = commitmentTerm;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}