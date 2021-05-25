package cache;

public class WriteBack {
	private Cache[] cache = new Cache[16];
	private MainMemory[] mainM= new MainMemory[256];
		
	public void initialCache(){	 
	     for(int i=0x0; i<=0xF;i++) {
	    	    int[] data = new int[16];
	    	    Cache Cache= new Cache();
	    	 	Cache.setValid(0);
				Cache.setTag(0);
				Cache.setDirty(0);
				for(int n=0x0; n<=0xF;n++) {
					data[n]=0;
					Cache.setData(data);
				}	
				cache[i]=Cache;
	    	}  	
	    }
	 public void initialMainMemory(){		
		 for(int i=0x00; i<=0xFF;i++) {
			 int[] data = new int[16];
			 MainMemory MainM= new MainMemory();
			 for(int n=0x0; n<=0xF;n++) {
				data[n]=((i&0x0F)<<4)+n;
				MainM.setData(data);
				
			}
			 mainM[i]=MainM;
		}
     }
	 
	public int calculateSlot(int address) {
		int slot=0;
		slot=(address&0x0F0)>>4;
		return slot;
	}
	
	public int calculateTag(int address) {
		int tag=0;
		tag=address>>8;
		return tag;
	}

	public int calculateOffset(int address) {
		int offset=0;
		offset= address&0xF;
		return offset;
	}
	
	public int calculateBlock_begin_addr(int address) {
		int Block_begin_addr=0;
		Block_begin_addr=(address& 0xFF0)>>4;
		return Block_begin_addr;
	}
	
	public void read(int address) {
		int slot= calculateSlot(address);
		int tag= calculateTag(address);
		int offset = calculateOffset(address);
		int block_begin_addr=calculateBlock_begin_addr(address);
		if(cache[slot].getValid()==0 && cache[slot].getDirty() ==0){
			cache[slot].setData(mainM[block_begin_addr].getData());
			cache[slot].setValid(1);
			System.out.println("At that byte there is the value  " + Integer.toHexString(cache[slot].getData()[offset]) + "  Cache Miss");
		}else if(cache[slot].getValid()==1 && cache[slot].getDirty() ==0){
			if(tag == cache[slot].getTag()){
				System.out.println("At that byte there is the value  " + Integer.toHexString(cache[slot].getData()[offset])  + "  Cache Hit");
			}else{
				cache[slot].setData(mainM[block_begin_addr].getData());
				System.out.println("At that byte there is the value  " + Integer.toHexString(cache[slot].getData()[offset])  + "  Cache Miss");  
			}
		}else if(cache[slot].getValid()==1 && cache[slot].getDirty() ==1){
			if(tag == cache[slot].getTag()){
				System.out.println("At that byte there is the value  " + Integer.toHexString(cache[slot].getData()[offset])  + "  Cache Hit");
			}else{
				mainM[slot].setData(cache[slot].getData());
				cache[slot].setData(mainM[block_begin_addr].getData());
		        cache[slot].setDirty(0);
		        System.out.println("At that byte there is the value  " + Integer.toHexString(cache[slot].getData()[offset])  + "  Cache Miss");
			}
	    }
    }
	
    public void write(int address, int value) {
    	int slot= calculateSlot(address);
		int tag= calculateTag(address);
		int offset = calculateOffset(address);
		int block_begin_addr=calculateBlock_begin_addr(address);
		if(cache[slot].getValid()==0 && cache[slot].getDirty() ==0){
			cache[slot].setData(mainM[block_begin_addr].getData());
			cache[slot].getData()[offset]=value;
			cache[slot].setValid(1);
			cache[slot].setDirty(1);
			System.out.println("Value" + value + "has been written to address  "+ address + ".  (Cache Miss)");
		}else if(cache[slot].getValid()==1 && cache[slot].getDirty() ==0){
			if(tag == cache[slot].getTag()){
				cache[slot].getData()[offset]=value;
				cache[slot].setDirty(1);
				System.out.println("Value" + value + "has been written to address  "+ address + ".  (Cache Hit)");
			}else{
				cache[slot].setData(mainM[block_begin_addr].getData());
				cache[slot].getData()[offset]=value;
				cache[slot].setDirty(1);
				System.out.println("Value" + value + "has been written to address  "+ address + ".  (Cache Miss)");
			}
		}else if(cache[slot].getValid()==1 && cache[slot].getDirty() ==1){
			if(tag == cache[slot].getTag()){
				cache[slot].getData()[offset]=value; 
				System.out.println("Value" + value + "has been written to address  "+ address + ".  (Cache Hit)");
			}else{
				mainM[slot].setData(cache[slot].getData());
				cache[slot].setData(mainM[block_begin_addr].getData());
				cache[slot].getData()[offset]=value;
		        cache[slot].setDirty(0);
		        System.out.println("Value" + value + "has been written to address  "+ address + ".  (Cache Miss)");
			}
	    }
		
	}
    public void Display() {
    	for(int i=0; i<16;i++) {
    		System.out.print(Integer.toHexString(i) + "  " + Integer.toHexString(cache[i].getValid()) + "  " + Integer.toHexString(cache[i].getTag()) + "  " );
    		for(int j=0; j<16;j++) {
    			System.out.print(Integer.toHexString(cache[i].getData()[j]) + "  ");
    		}
    		System.out.println("");
    	}
        /**
    	for(int i=0; i<256;i++) {
    		System.out.println(Integer.toHexString(i) + "  ");
    		for(int j=0; j<16;j++) {
    			System.out.print(Integer.toHexString(mainM[i].getData()[j]));
    		}
    		System.out.println("");
    	}*/
    		}
    	
	

}
