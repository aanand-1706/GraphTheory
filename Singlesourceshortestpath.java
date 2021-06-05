import java.util.*;
import java.io.*;

public class Singlesourceshortestpath{

    static List<Integer> topo_ordering=new ArrayList<>();
    static Set<Integer> visited=new HashSet<>();
    static int[] indegree;
    static int[] distance;

    static class Edge{
        int from,to,weight;
        Edge(int from,int to,int weight){
            this.from=from;
            this.to=to;
            this.weight=weight;
        }
    }
    public static void main(String[] args){
        System.out.println("name");
        Map<Integer,List<Edge>> graph=new HashMap<>();
        int N=7;
        indegree=new int[N];
        distance=new int[N];
        Arrays.fill(distance,Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) 
         graph.put(i, new ArrayList<>());

        graph.get(0).add(new Edge(0, 1, 3));
        graph.get(0).add(new Edge(0, 2, 2));
        graph.get(0).add(new Edge(0, 5, 3));
        graph.get(1).add(new Edge(1, 3, 1));
        graph.get(1).add(new Edge(1, 2, 6));
        graph.get(2).add(new Edge(2, 3, 1));
        graph.get(2).add(new Edge(2, 4, 10));
        graph.get(3).add(new Edge(3, 4, 5));
        graph.get(5).add(new Edge(5, 4, 7));
        indegree[1]=1;
        indegree[2]=2;
        indegree[3]=2;
        indegree[4]=3;
        indegree[5]=1;
        
        topologicalSort(graph,0);
        shortestpath(graph);

        for(int i=0;i<topo_ordering.size();i++){
            System.out.println("distance to "+i+":"+distance[i]);
        }

    }
   
    public static void topologicalSort(Map<Integer,List<Edge>> graph,int start){
        visited.add(start);
        Queue<Integer> que=new LinkedList<>();
        int[] degree=new int[indegree.length];
        for(int i=0;i<degree.length;i++){
            degree[i]=indegree[i];
        }
        for(int i=0;i<indegree.length;i++){
            if(degree[i]==0){
                que.add(i);
            }
        }
        while(!que.isEmpty()){
            
            int size=que.size();
            while(size>0){
                int cur=que.poll();
                visited.add(cur);
                topo_ordering.add(cur);
                if(graph.get(cur)!=null){
                    for(Edge e:graph.get(cur)){
                        if(!visited.contains(e.to)){
                            degree[e.to]--;
                            if(degree[e.to]==0)
                                que.offer(e.to);
                        }
                    }
                }
                size--;
            }
        }
        
        for(int node:topo_ordering){
            System.out.print(node+" ");
        }
        System.out.println();
        // Collections.reverse(topo_ordering);
    }

    public static void shortestpath(Map<Integer,List<Edge>> graph){
        // distance[topo_ordering.get(0)]=0;
        distance[0]=0;
        for(int i=0;i<topo_ordering.size();i++){
            int cur=topo_ordering.get(i);
            // System.out.println("element "+cur+" indegree "+ indegree[cur]);
            // if(indegree[cur]==0)
            //     distance[cur]=0;
            if(graph.get(cur)!=null){
                for(Edge e:graph.get(cur)){
                    distance[e.to]=Math.min(e.weight+distance[cur],distance[e.to]);
                }
            }
        }
    }

}