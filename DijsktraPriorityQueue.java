import java.io.*;
import java.util.*;

public class DijsktraPriorityQueue {
    static Map<Integer,List<Edge>> graph=new HashMap<>();
    static int[] distance;
    static Integer[] prev;
    
    public static void main(String[] args){
        int N=5;
        distance=new int[N];
        prev=new Integer[N];

        Arrays.fill(distance,Integer.MAX_VALUE);
        for(int i=0;i<N;i++){
            graph.put(i,new ArrayList<>());
        }
        graph.get(0).add(new Edge(1,4));
        graph.get(0).add(new Edge(2,1));
        graph.get(1).add(new Edge(3,1));
        graph.get(2).add(new Edge(1,2));
        graph.get(2).add(new Edge(3,5));
        graph.get(3).add(new Edge(4,3));

        dijsktraAlgo(graph);

        for(int i=0;i<N;i++){
            System.out.println("distance from 0 to "+i+"is :"+distance[i]);
        }

        System.out.println("shortest path from 0 to 4 is");
        for(Integer cur=4;cur!=null;cur=prev[cur])
            System.out.println(cur);
    }

    public static void dijsktraAlgo(Map<Integer,List<Edge>> graph){
        PriorityQueue<int[]> que=new PriorityQueue<>((a,b)->(a[1]-b[1]));
        boolean visited[]=new boolean[graph.size()];
        distance[0]=0;
        prev[0]=null;
        que.offer(new int[]{0,0});

        while(!que.isEmpty()){
            int cur[]=que.poll();
            if(visited[cur[0]])
                continue;

            if(distance[cur[0]]<cur[1])
                continue;
            
            for(Edge e:graph.get(cur[0])){
                if(!visited[e.to]){
                    int newDist=distance[cur[0]]+e.weight;
                    if(newDist<distance[e.to]){
                        distance[e.to]=newDist;
                        prev[e.to]=cur[0];
                        que.offer(new int[]{e.to,newDist});
                    }
                }
            }
            visited[cur[0]]=true;
        }
    }


    static class Edge{
        int to,weight;
        Edge(int t,int w){
            to=t;
            weight=w;
        }
    }
}
