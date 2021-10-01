package YabinglePack;

import java.util.ArrayList;

public class PriorityList<T extends Comparable>
{
    ArrayList<T> list = new ArrayList<>();
    int size = 0;

    public void Sort()
    {
        for(int i = size - 1; i > 0; i--)
        {
            if (list.get(i).compareTo(list.get(i-1)) == 1)
            {
                T temp = list.get(i);
                list.set(i,list.get(i - 1));
                list.set(i - 1, temp);
            }
            else
            {
                return;
            }
        }
    }

    public void Add(T type)
    {
        list.add(type);
        size++;
        Sort();
    }
    
    public void Clear()
    {
        list.clear();
        size = 0;
    }

    public int Count()
    {
        return list.size();
    }

    public T First()
    {
        return list.get(0);
    }

    public T At(int index)
    {
        return list.get(index);
    }

    public void Remove(T type)
    {
        list.remove(type);
        size--;
    }

    public boolean Contains(T type)
    {
        return list.contains(type);
    }

    public void Pop()
    {
        list.remove(0);
        size--;
    }
}
