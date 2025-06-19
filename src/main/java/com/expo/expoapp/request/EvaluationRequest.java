package com.expo.expoapp.request;

import java.util.List;

public record EvaluationRequest(String comment, String projectId, List<EvaluateRequest> evaluateRequests) {
}
