package tk.dbcore.commons;

import lombok.Data;

import java.util.List;

/**
 * Project: amugona
 * FileName: ErrorResponse
 * Date: 2015-09-15
 * Time: 오전 10:26
 * Author: Hadeslee
 * Note:
 * To change this template use File | Settings | File Templates.
 */
@Data
public class ErrorResponse {

    private String message;

    private String code;

    private List<FiledError> errors;

    public static class FiledError {

        private String filed;
        private String value;
        private String reason;
    }

}
