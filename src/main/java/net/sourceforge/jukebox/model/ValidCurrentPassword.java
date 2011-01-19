/*
 * Copyright 2010 Raghuram
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sourceforge.jukebox.model;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Annotation to match passwords.
 */
@Documented
@Constraint(validatedBy = CurrentPasswordValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ValidCurrentPassword {

    /**
     * Default message for the annotation.
     */
    String message() default "{validator.current.password}";

    /**
     * Default group for the annotation.
     */
    Class<?>[] groups() default { };

    /**
     * Default payload for the annotation.
     */
    Class<? extends Payload>[] payload() default { };
}
