package kafka;

public class VisualizationPayload {

    private String status;

    private String workflowName;

    private String stage;

    private String message;
    
    private String reqId;

    private Long timeRequired;

    private String methodArgs;

    private String errorStack;

    private Long timestamp;

    public VisualizationPayload(String reqId, String workflowName, String stage) {
        this.workflowName = workflowName;
        this.stage = stage;
        this.reqId = reqId;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJobId() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public Long getTimeRequired() {
        return timeRequired;
    }

    public void setTimeRequired(Long timeRequired) {
        this.timeRequired = timeRequired;
    }

    public String getMethodArgs() {
        return methodArgs;
    }

    public void setMethodArgs(String methodArgs) {
        this.methodArgs = methodArgs;
    }

    public String getErrorStack() {
        return errorStack;
    }

    public void setErrorStack(String errorStack) {
        this.errorStack = errorStack;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}