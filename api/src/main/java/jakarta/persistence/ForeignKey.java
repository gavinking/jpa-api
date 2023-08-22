/*
 * Copyright (c) 2011, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */

// Contributors:
//     Linda DeMichiel - 2.1

package jakarta.persistence;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static jakarta.persistence.ConstraintMode.CONSTRAINT;

/**
 * Used to specify the handling of foreign key constraints when schema
 * generation is in effect.  If this annotation is not specified, the
 * persistence provider's default foreign key strategy will be used.
 * <p>
 * The <code>ConstraintMode</code> value is used to specify whether foreign
 * key constraints should be generated.
 * <p>
 * The syntax used in the <code>foreignKeyDefinition</code> element 
 * should follow the SQL syntax used by the target database for foreign
 * key constraints.  For example, this may be similar the following:
 * <pre>
 * FOREIGN KEY ( &#060;COLUMN expression&#062; {, &#060;COLUMN expression&#062;}... )
 * REFERENCES &#060;TABLE identifier&#062; [
 *     (&#060;COLUMN expression&#062; {, &#060;COLUMN expression&#062;}... ) ]
 * [ ON UPDATE &#060;referential action&#062; ]
 * [ ON DELETE &#060;referential action&#062; ]
 * </pre>
 *
 * When the <code>ConstraintMode</code> value is
 * <code>CONSTRAINT</code>, but the <code>foreignKeyDefinition</code>
 * element is not specified, the provider will generate foreign key
 * constraints whose update and delete actions it determines most
 * appropriate for the join column(s) to which the foreign key
 * annotation is applied.
 *
 * @see JoinColumn
 * @see JoinColumns
 * @see MapKeyJoinColumn
 * @see MapKeyJoinColumns
 * @see PrimaryKeyJoinColumn
 * @see JoinTable
 * @see CollectionTable
 * @see SecondaryTable
 * @see AssociationOverride
 *
 * @since 2.1
 */
@Target({})
@Retention(RUNTIME)
public @interface ForeignKey {

    /**
     * (Optional) The name of the foreign key constraint.
     * <p> Defaults to a provider-generated name.
     */
    String name() default "";

    /**
     * (Optional) Used to specify whether a foreign key constraint
     * should be generated when schema generation is in effect.
     * <ul>
     * <li><code>CONSTRAINT</code> specifies that the persistence
     * provider must generate a foreign key constraint. If the
     * <code>foreignKeyDefinition</code> element is not specified,
     * the provider will generate a constraint whose update and
     * delete actions it determines most appropriate for the join
     * column or columns to which the foreign key annotation is
     * applied.
     * <li><code>NO_CONSTRAINT</code> specifies that no constraint
     * should be generated.
     * <li><code>PROVIDER_DEFAULT</code> selects the default
     * behavior of the provider, which may or may not result in
     * generation of a constraint.
     * </ul>
     */
    ConstraintMode value() default CONSTRAINT;

    /**
     * (Optional) The foreign key constraint definition.  
     */
    String foreignKeyDefinition() default "";

    /**
     * (Optional) A SQL fragment appended to the generated DDL
     * which creates this foreign key. May not be used in
     * conjunction with {@link #foreignKeyDefinition()}.
     *
     * @since 3.2
     */
    String options() default "";
}
