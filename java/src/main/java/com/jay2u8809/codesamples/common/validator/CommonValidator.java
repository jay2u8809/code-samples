package com.jay2u8809.codesamples.common.validator;

import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class CommonValidator implements Validator {

    protected Errors checkPostTitle(Errors errors, String postTitle) {
        Object[] arg = {"Post Title"};

        if (ObjectUtils.isEmpty(postTitle)) {
//            errors.rejectValue("PostTitle", "Post Title is Empty", arg, "error");
            errors.rejectValue("PostTitle", "Post Title is Empty");
        }

        return errors;
    }
}
