package cache;

public class Cache {
    private int valid;
    private int tag;
    private int dirty;
    private int[] data= new int[16];
    
    public Cache() {
    	
    }
    public Cache(int valid, int tag,int dirty, int[] data) {
        this.valid = valid;
        this.tag = tag;
        this.dirty = dirty;
        this.data= data;
        
    }
    
    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
    public int getDirty() {
        return dirty;
    }

    public void setDirty(int dirty) {
        this.dirty = dirty;
    }
    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

}
