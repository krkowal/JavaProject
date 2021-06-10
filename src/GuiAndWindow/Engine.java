package GuiAndWindow;

import AnimalTesting.Field;
import AnimalTesting.GeneratedMap;
import AnimalTesting.Vector2d;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

public class Engine extends Canvas implements Runnable{

    private static final long serialVersionUID = -2081588595790118997L;
//	private static final int WIDTH = 600, HEIGHT = 400;
	private int guiWidth,guiHeight;
    private Thread thread1,thread2;
    private boolean running = true;



	GeneratedMap map;

    public Engine(int guiWidth, int guiHeight, boolean jng, int guiAnimalEnergy, int guiEnergyDecreaseByDay, int guiStartingAnimalsCount, int guiStartingGrassCount, int guiGrassIncreaseByDay) {
        new Window(guiWidth+15,guiHeight+38,jng,"gra",this);
        map= new GeneratedMap(guiWidth/20,guiHeight/20,guiStartingAnimalsCount,guiAnimalEnergy,guiEnergyDecreaseByDay,guiStartingGrassCount,guiGrassIncreaseByDay);
    }



	public synchronized void start() {
		thread1 = new Thread(this);
		thread1.start();
//		thread2 = new Thread(this);
//		thread2.start();
//		thread3 = new Thread(this);
//		thread3.start();
//		thread4 = new Thread(this);
//		thread4.start();
//		thread5 = new Thread(this);
//		thread5.start();
		running = true;
    }
    public synchronized void stop() {
		try {
			thread1.join();
//			thread2.join();
//			thread3.join();
//			thread4.join();
//			thread5.join();
			running = false;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

    }

    public void run() {
		long lastTime= System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		long timer  = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while (delta >=1) {
				tick();
				delta --;
			}
			if (running)
				render();
			frames++;
			if (System.currentTimeMillis()-timer>1000) {
				timer+=1000;
				System.out.println("FPS: "+frames);
			}
		}
		stop();
    }
	private void tick() {
    	if(map!=null) {


			map.removeDeadAnimals();
			map.moveAnimals();
			map.animalsEatOnFields();
			map.animalsCopulateOnFields();
			map.addGrass();
		}
	}
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		mapRender(g);
		g.dispose();
		bs.show();
	}
    public void mapRender(Graphics g){
        if(map!=null){
			map.getFields().values().forEach(field->field.render(g));
//    	for(int i=0;i<guiWidth/4;i++){
//            for(int j=0;j<guiHeight/4;j++){
//                Field tempField = map.getFields().get(new Vector2d(j,i));
//                tempField.render(g);
//            }
        }
    }
}

