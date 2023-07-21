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

    }

   
}
