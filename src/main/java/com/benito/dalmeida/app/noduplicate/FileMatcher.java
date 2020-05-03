package com.benito.dalmeida.app.noduplicate;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class FileMatcher {

    private String shortName;
    private String longName;
    private String ext;
    private Date creationDate;
    private Date modificationDate;
    private Double fileSize;

    public boolean areSimilar(final FileMatcher f1, final FileMatcher f2) {
        final String shorterName = f1.getShortName().length() > f2.getShortName().length() ? f2.getShortName() : f1
                .getShortName();
        if (f1.getFileSie().compareTo(f2.getFileSie()) == 0
                && (StringUtils.containsIgnoreCase(f1.getShortName(), shorterName) || StringUtils.containsIgnoreCase(
                        f2.getShortName(), shorterName))) {
            return true;
        }
        return false;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(final String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(final String longName) {
        this.longName = longName;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(final String ext) {
        this.ext = ext;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(final Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Double getFileSie() {
        return fileSize;
    }

    public void setFileSie(final Double fileSie) {
        this.fileSize = fileSie;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((ext == null) ? 0 : ext.hashCode());
        result = prime * result + ((fileSize == null) ? 0 : fileSize.hashCode());
        result = prime * result + ((longName == null) ? 0 : longName.hashCode());
        result = prime * result + ((modificationDate == null) ? 0 : modificationDate.hashCode());
        result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final FileMatcher other = (FileMatcher) obj;
        if (creationDate == null) {
            if (other.creationDate != null)
                return false;
        } else if (!creationDate.equals(other.creationDate))
            return false;
        if (ext == null) {
            if (other.ext != null)
                return false;
        } else if (!ext.equals(other.ext))
            return false;
        if (fileSize == null) {
            if (other.fileSize != null)
                return false;
        } else if (!fileSize.equals(other.fileSize))
            return false;
        if (longName == null) {
            if (other.longName != null)
                return false;
        } else if (!longName.equals(other.longName))
            return false;
        if (modificationDate == null) {
            if (other.modificationDate != null)
                return false;
        } else if (!modificationDate.equals(other.modificationDate))
            return false;
        if (shortName == null) {
            if (other.shortName != null)
                return false;
        } else if (!shortName.equals(other.shortName))
            return false;
        return true;
    }
}
