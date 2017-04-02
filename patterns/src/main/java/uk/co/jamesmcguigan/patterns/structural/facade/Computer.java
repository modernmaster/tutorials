package uk.co.jamesmcguigan.patterns.structural.facade;

public class Computer {
    private CPU processor;
    private Memory ram;
    private HardDrive hd;

    private static final long BOOT_ADDRESS = 123456789;
    private static final long BOOT_SECTOR = 123456789;
    private static final int SECTOR_SIZE = 123456789;

    public Computer() {
        this.processor = new CPU();
        this.ram = new Memory();
        this.hd = new HardDrive();
    }

    public void start() {
        processor.freeze();
        ram.load(BOOT_ADDRESS, hd.read(BOOT_SECTOR, SECTOR_SIZE));
        processor.jump(BOOT_ADDRESS);
        processor.execute();
    }
}
