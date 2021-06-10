//package GuiAndWindow;
//
//import AnimalTesting.Field;
//import AnimalTesting.GeneratedMap;
//
//import java.awt.*;
//import java.util.LinkedList;
//
//public class Handler {
//    LinkedList<Field> object = new LinkedList<>();
//    GeneratedMap map;
//    public Handler(GeneratedMap map){
//        this.map=map;
//    }
//    public void addFields(){
//        for(Field field : map.getFields().values()){
//            object.add(field);
//        }
//    }
//    public void tick(){
//        for(int i=0; i<object.size();i++){
//            Field tempField = object.get(i);
//            tempField.tick();
//        }
//
//    }
//    public void render(Graphics g){
//        for(int i =0; i<object.size();i++){
//            Field tempField = object.get(i);
//            tempField.render(g);
//        }
//    }
//    public void addObject(Field field){
//        this.object.add(field);
//    }
//    public void removeObject(Field field){
//        this.object.remove(field);
//    }
//}
