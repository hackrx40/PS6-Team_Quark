using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;



public class DistanceController : MonoBehaviour
{
    public Text distance_on_screen;
    public Transform user;
    public LineRenderer lineRenderer;
    public GameObject chest_reward;
    public GameObject spotLight;

    public float timer;
    public float distance;
    public int int_distance;
    public int int_timer;

    int i, k;
    float virtual_distance;
    float real_distance;
    int start;
    int reward_dist;


    Vector3 position = new Vector3();


    void Start()
    {
        start = 1;                           
        reward_dist = 100;
    }

    void Update()
    {
        position = user.position;
        timer += Time.deltaTime;                                        
        int_timer = (int)timer;

        if (start == int_timer)
        {
            start++;
            position_list.Add(position);                                
            lineRenderer.positionCount = position_list.Count;

            if (start != 2)                                            
            {
                lineRenderer.SetPosition(k, position_list[position_list.Count - 1]);
                lineRenderer.SetPosition(k + 1, position_list[position_list.Count - 2]);
                k++;

                i = position_list.Count - 1;
                virtual_distance = DistanceBetweenPoints(position_list, i, i - 1);
                distance += virtual_distance;
                //distance *= 0.201078457f;
                real_distance = distance * 3.709071692f;                
                int_distance = (int)real_distance;

                distance_on_screen.text = "Distance: " + int_distance.ToString() + "m";
            }

            RewardHandler();


        }
    }

    float DistanceBetweenPoints(List<Vector3> position_list, int a, int b)
    {
        return Mathf.Sqrt(((position_list[a].x - position_list[b].x) * (position_list[a].x - position_list[b].x)) +
                    ((position_list[a].y - position_list[b].y) * (position_list[a].y - position_list[b].y)) +
                    ((position_list[a].z - position_list[b].z) * (position_list[a].z - position_list[b].z)));
    }

   
}
