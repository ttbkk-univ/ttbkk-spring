package com.ttbkk.api.test;

import com.ttbkk.api.common.exception.CustomErrorType;
import com.ttbkk.api.common.exception.RestApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test")
@Controller
public class TestController {

    /**
     * test 용 controller.
     * @return ResponseEntity
     */
    @GetMapping("/runtimeException")
    public ResponseEntity<String> testRuntimeException() {
        String val = "test";
        val.charAt(5);
        return new ResponseEntity<>("testRuntimeException", HttpStatus.OK);
    }

    /**
     * test 용 controller.
     * @return ResponseEntity
     */
    @GetMapping("/restApiException")
    public ResponseEntity<String> testRestApiException() {
        String val = "test";
        int n = 5;
        if (val.length() < n) {
            throw new RestApiException(CustomErrorType.INVALID_COORDINATE_PARAMETER, "범위 초과");
        }
        val.charAt(5);
        return new ResponseEntity<>("testRestApiException", HttpStatus.OK);
    }
}
