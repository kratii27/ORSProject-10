package com.rays.common;

/**
 * Interface representing a contract for objects that can be used
 * to populate dropdown lists in the ORS application.
 * <p>
 * Any entity or DTO that needs to be displayed in a dropdown UI component
 * should implement this interface and provide meaningful key-value pairs.
 * </p>
 *
 * @author Krati Trivedi
 */
public interface DropdownList {

    /**
     * Returns the key associated with this dropdown item,
     * typically the primary key or unique identifier of the entity.
     *
     * @return the key as a {@link String}
     */
    public String getKey();

    /**
     * Returns the display value associated with this dropdown item,
     * typically a human-readable name or label shown in the UI.
     *
     * @return the display value as a {@link String}
     */
    public String getValue();
}