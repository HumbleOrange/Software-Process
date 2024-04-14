import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.his.outpatient.dao.entity.Disecategory;
import org.example.his.outpatient.dao.mapper.DisecategoryMapper;
import org.example.his.outpatient.service.DisecategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.baomidou.mybatisplus.core.toolkit.StringPool.NULL;

/**
 * (Disecategory)表服务实现类
 *
 * @author makejava
 * @since 2023-07-09 10:22:39
 */
@Service("disecategoryService")
public class DisecategoryServiceImpl implements DisecategoryService {
    @Resource
    private DisecategoryMapper disecategoryDao;

    @Autowired
    DisecategoryMapper disecategoryMapper;


    @Override
    public int addDiseCategory(String DicaCode, String DicaName, int SequenceNo) {
        Disecategory disecategory = new Disecategory();
        disecategory.setDicaCode(DicaCode);
        disecategory.setDelMark(1);
        disecategory.setDicaName(DicaName);
        disecategory.setDicaType(1);
        disecategory.setSequenceNo(SequenceNo);
        int result = disecategoryMapper.insert(disecategory);
        return result;
    }

    @Override
    public int updateDiseCategory(int id, String DicaCode, String DicaName, int SequenceNo) {
        Disecategory disecategory = new Disecategory();
        disecategory.setDicaCode(DicaCode);
        disecategory.setId(id);
        disecategory.setDelMark(1);
        disecategory.setDicaName(DicaName);
        disecategory.setDicaType(1);
        disecategory.setSequenceNo(SequenceNo);
        int result = disecategoryMapper.updateById(disecategory);
        return result;
    }

    @Override
    public Map<String, Object> getAllByDicaCode(int page, int count, String keywords) {
        QueryWrapper<Disecategory> queryWrapper = new QueryWrapper<>();
        if(keywords!="" && keywords!=NULL)
            queryWrapper.eq("dicaCode", keywords);
        Page<Disecategory> newPage = new Page<>();
        newPage.setCurrent(page);
        newPage.setSize(count);
        newPage = (Page<Disecategory>) disecategoryMapper.selectPage(newPage,queryWrapper);
        List<Disecategory> DisList = newPage.getRecords();
        long total = newPage.getTotal();
        Map<String, Object> result = new HashMap<>();

        List<Map<String, Object>> resultList = new ArrayList<>();

        for(Disecategory disecategory: DisList){
            Map<String,Object> map = new HashMap<>();
            map.put("sequenceNo",disecategory.getSequenceNo());
            map.put("dicaCode",disecategory.getDicaCode());
            map.put("dicaName",disecategory.getDicaName());
            resultList.add(map);
        }
        result.put("status",200);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("list",resultList);
        map1.put("total",total);
        result.put("data",map1);
        return result;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Disecategory queryById(Integer id) {
        return this.disecategoryDao.selectById(id);
    }

    /**
     * 新增数据
     *
     * @param disecategory 实例对象
     * @return 实例对象
     */
    @Override
    public Disecategory insert(Disecategory disecategory) {
        this.disecategoryDao.insert(disecategory);
        return disecategory;
    }

    /**
     * 修改数据
     *
     * @param disecategory 实例对象
     * @return 实例对象
     */
    @Override
    public Disecategory update(Disecategory disecategory) {
        this.disecategoryDao.updateById(disecategory);
        return this.queryById(disecategory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.disecategoryDao.deleteById(id) > 0;
    }

    @Override
    public List<Disecategory> getDiseaseByType(Integer page, Integer count, String DicaType) {
        return null;
    }
}
