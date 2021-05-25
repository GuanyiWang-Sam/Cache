package cache;

public class MainMemory {
	    //private int block_begin_addr;
	    private int[] data= new int[16];
	    
	    public MainMemory() {
	    	
	    }
	    public MainMemory(int[] data) {
	        //this.block_begin_addr = tag;
	        this.data= data;
	        
	    }
	    /**
	    public int getBlock_begin_addr() {
	        return block_begin_addr;
	    }

	    public void setBlock_begin_addr(int block_begin_addr) {
	        this.block_begin_addr = block_begin_addr;
	    }*/
	    
	    public int[] getData() {
	        return data;
	    }

	    public void setData(int[] data) {
	        this.data = data;
	    }

	}

