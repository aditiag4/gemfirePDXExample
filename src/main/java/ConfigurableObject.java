/*
 * Copyright (c) VMware, Inc. 2023. All rights reserved.
 */

/*
 * ================================================================================================
 * Copyright (c) 2002-2014 Pivotal Software, Inc. All Rights Reserved. This product is protected by
 * U.S. and international copyright and intellectual property laws. Pivotal products are covered by
 * more patents listed at http://www.pivotal.io/patents.
 * ================================================================================================
 */

/**
 * Interface for configurable objects that encode an <code>int</code> key ("index"). An object type
 * can be configured using its corresponding parameter class, if one exists.
 */

public interface ConfigurableObject {

    /**
     * Returns a new instance of the object encoded with the index.
     *
     * @throws ObjectCreationException An error occurred when creating the object. See the error
     *         message for more details.
     */
    public void init(int index);

    /**
     * Returns the index encoded in the object.
     *
     * @throws ObjectAccessException An error occurred when accessing the object. See the error
     *         message for more details.
     */
    public int getIndex();

    /**
     * Validates whether the index is encoded in the object, if this applies, and performs other
     * validation checks as needed.
     *
     * @throws ObjectAccessException An error occurred when accessing the object. See the error
     *         message for more details.
     * @throws ObjectValidationException The object failed validation. See the error message for more
     *         details.
     */
    public void validate(int index);
}